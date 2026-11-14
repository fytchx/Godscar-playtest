import puppeteer from 'puppeteer-core';
import assert from 'node:assert/strict';
import fs from 'node:fs';

const BROWSER = '/usr/bin/google-chrome';
const browser = await puppeteer.launch({
  executablePath: BROWSER,
  headless: true,
  args: ['--no-sandbox', '--disable-gpu', '--disable-dev-shm-usage'],
});
fs.mkdirSync('screenshots', { recursive: true });
try {
  for (const width of [360, 390, 412, 480]) {
    const page = await browser.newPage();
    await page.setViewport({ width, height: 844, deviceScaleFactor: 1 });
    const errors = [];
    page.on('pageerror', error => errors.push(error.message));
    page.on('response', response => {
      if (response.status() >= 400 && response.url().includes('/assets/ui/')) {
        errors.push(response.status() + ' ' + response.url());
      }
    });
    await page.goto('http://127.0.0.1:8080/', { waitUntil: 'networkidle0' });
    await page.waitForSelector('#recapPanel.open');
    await page.click('.recap-footer .primary-button');
    await page.waitForSelector('#recapPanel:not(.open)');
    await page.click('.zone-button');
    await page.waitForSelector('#zonePanel.open');
    await page.click('#zonePanel .close-button');
    await page.waitForSelector('#zonePanel:not(.open)');
    await page.click('.settings-button');
    await page.waitForSelector('#settingsPanel.open');
    await page.click('#settingsPanel .close-button');
    await page.waitForSelector('#settingsPanel:not(.open)');
    await page.click('.feed-head .icon-button');
    await page.waitForSelector('#filterPanel.open');
    await page.click('#filterPanel .close-button');
    await page.waitForSelector('#filterPanel:not(.open)');
    // Character remains a mock-only overlay and must not replace the Fight arena.
    await page.click('#characterNav');
    await page.waitForSelector('#characterScreen:not([hidden])');
    assert.equal(await page.evaluate(() => document.querySelectorAll('#characterEquipmentGrid .gs-character__slot').length), 11);
    assert.equal(await page.$eval('.gs-character__slot--helmet .gs-character__gearscore strong', node => node.textContent), '2,485');
    const centered = await page.evaluate(() => {
      const legs=document.querySelector('.gs-character__slot--legs').getBoundingClientRect();
      const boots=document.querySelector('.gs-character__slot--boots').getBoundingClientRect();
      const grid=document.querySelector('#characterEquipmentGrid').getBoundingClientRect();
      return Math.abs((legs.left+boots.right)/2-(grid.left+grid.right)/2)<3;
    });
    assert.ok(centered, 'Legs and Boots must be centered');
    await page.click('#characterDetailsTrigger');
    await page.waitForSelector('#characterDetailsLayer:not([hidden])');
    if(width===390)await page.screenshot({path:'screenshots/character-details-390.png'});
    await page.click('#characterDetailsDismiss');
    await page.waitForSelector('#characterDetailsLayer[hidden]');
    if(width===390)await page.screenshot({path:'screenshots/character-equipment-390.png',fullPage:true});
    await page.click('[data-character-tab="inventory"]');
    await page.waitForSelector('#charInventory:not([hidden])');
    assert.ok(await page.evaluate(() => document.querySelectorAll('#characterItemsList .gs-character__item').length >= 4));
    await page.click('#characterItemsList [data-item-index="0"]');
    await page.waitForSelector('#characterItemLayer:not([hidden])');
    await page.click('#characterItemClose');
    await page.waitForSelector('#characterItemLayer[hidden]');
    await page.click('[data-inventory-tab="materials"]');
    await page.waitForSelector('#charMaterials:not([hidden])');
    assert.ok(await page.evaluate(() => document.querySelectorAll('#characterMaterialsList .gs-character__material').length >= 3));
    await page.click('[data-character-tab="skills"]');
    await page.waitForSelector('#charSkills:not([hidden])');
    await page.click('.bottom-nav .nav-button:first-child');
    await page.waitForSelector('#characterScreen[hidden]');
    assert.ok(await page.$eval('.arena',node=>node.getBoundingClientRect().width)>250);
    const state = await page.evaluate(() => {
      const icons = [...document.querySelectorAll('img.gs-icon')];
      const css = document.querySelector('link[href="assets/ui/godscar-ui.css"]');
      const reskin = document.querySelector('link[href="assets/ui/arcana-reskin.css"]');
      const hollow = document.querySelector('link[href="assets/ui/hollow-steel-integration.css"]');
      const slot = document.querySelector('.gs-character__slot-visual');
      const navButton = document.querySelector('.nav-button.gs-nav-item[aria-current="page"]');
      const panel = document.querySelector('.feed-card.gs-feed-shell');
      const feed = document.querySelector('.feed-card.gs-feed-shell');
      const nav = document.querySelector('.bottom-nav.gs-navbar');
      const arena = document.querySelector('.arena');
      const absX = document.documentElement.scrollWidth - window.innerWidth;
      return {
        horizontalOverflow: absX,
        icons: icons.length,
        loadedIcons: icons.filter(x => x.complete && x.naturalWidth > 0).length,
        cssLoaded: Boolean(css?.sheet),
        reskinLoaded: Boolean(reskin?.sheet),
        hollowLoaded: Boolean(hollow?.sheet),
        themeColor:getComputedStyle(document.documentElement).getPropertyValue('--hs-sea').trim(),
        panelSvg:getComputedStyle(panel,'::before').borderImageSource,
        navSvg:getComputedStyle(navButton,'::before').borderImageSource,
        slotSvg:getComputedStyle(slot).backgroundImage,
        iconSources:icons.filter(x=>x.src.includes('/assets/ui/hollow-steel/icons/')).length,
        navVisible: Boolean(nav && getComputedStyle(nav).display !== 'none'),
        feedRadius: getComputedStyle(feed).borderRadius,
        surface: getComputedStyle(feed).backgroundColor,
        arenaWidth: arena?.getBoundingClientRect().width || 0,
        buttons: [...document.querySelectorAll('.gs-button')].length,
      };
    });
    assert.equal(state.horizontalOverflow <= 1, true, 'Horizontal overflow at '+width);
    assert.equal(state.icons, state.loadedIcons, 'Broken icons at '+width);
    assert.equal(state.cssLoaded, true, 'Missing kit stylesheet at '+width);
    assert.equal(state.navVisible, true, 'Missing bottom nav at '+width);
    assert.equal(state.reskinLoaded, true, 'Missing base redesign stylesheet at '+width);
    assert.equal(state.hollowLoaded, true, 'Missing Hollow Steel integration CSS at '+width);
    assert.equal(state.themeColor, '#79BFB3', 'Wrong theme accent at '+width);
    assert.match(state.panelSvg, /GS-UI-CMP-001_panel-frame.svg/, 'Panel SVG not in use at '+width);
    assert.match(state.navSvg, /GS-UI-CMP-008_nav-selected.svg/, 'Nav SVG not in use at '+width);
    assert.match(state.slotSvg, /GS-UI-CMP-010_slot/, 'Equipment well SVG not in use at '+width);
    assert.equal(state.iconSources, state.icons, 'Not all icons use Hollow Steel SVG files');
    assert.notEqual(state.feedRadius, '0px', 'Missing Arcana-style card radius at '+width);
    assert.ok(state.arenaWidth > 250, 'Arena too narrow at '+width);
    assert.ok(state.buttons >= 8, 'Reusable buttons not applied');
    assert.deepEqual(errors, [], 'Page errors at '+width);
    await page.screenshot({ path: 'screenshots/fight-'+width+'.png', fullPage: true });
    console.log('PASS', width, JSON.stringify(state));
    await page.close();
  }
} finally {
  await browser.close();
}
