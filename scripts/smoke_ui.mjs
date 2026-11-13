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
    const state = await page.evaluate(() => {
      const icons = [...document.querySelectorAll('img.gs-icon')];
      const css = document.querySelector('link[href="assets/ui/godscar-ui.css"]');
      const feed = document.querySelector('.feed-card.gs-feed-shell');
      const nav = document.querySelector('.bottom-nav.gs-navbar');
      const arena = document.querySelector('.arena');
      const absX = document.documentElement.scrollWidth - window.innerWidth;
      return {
        horizontalOverflow: absX,
        icons: icons.length,
        loadedIcons: icons.filter(x => x.complete && x.naturalWidth > 0).length,
        cssLoaded: Boolean(css?.sheet),
        navVisible: Boolean(nav && getComputedStyle(nav).display !== 'none'),
        feedFrame: getComputedStyle(feed).clipPath,
        arenaWidth: arena?.getBoundingClientRect().width || 0,
        buttons: [...document.querySelectorAll('.gs-button')].length,
      };
    });
    assert.equal(state.horizontalOverflow <= 1, true, 'Horizontal overflow at '+width);
    assert.equal(state.icons, state.loadedIcons, 'Broken icons at '+width);
    assert.equal(state.cssLoaded, true, 'Missing kit stylesheet at '+width);
    assert.equal(state.navVisible, true, 'Missing bottom nav at '+width);
    assert.notEqual(state.feedFrame, 'none', 'Missing feed frame at '+width);
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
