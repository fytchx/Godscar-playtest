import puppeteer from 'puppeteer-core';
import assert from 'node:assert/strict';
import fs from 'node:fs';
const browser=await puppeteer.launch({executablePath:'/usr/bin/google-chrome',headless:true,args:['--no-sandbox','--disable-gpu','--disable-dev-shm-usage']});
fs.mkdirSync('screenshots',{recursive:true});
const widths=[360,390,480];
try {
 for(const width of widths) {
  const page=await browser.newPage();
  await page.setViewport({width,height:840,deviceScaleFactor:1});
  const failures=[];
  page.on('pageerror',err=>failures.push(err.message));
  page.on('response',r=>{if(r.status()>=400)failures.push(String(r.status())+' '+r.url())});
  await page.goto('http://127.0.0.1:8080/review/hollow-steel-batch-01.html',{waitUntil:'networkidle0'});
  const before=await page.evaluate(()=>{
   const panel=document.getElementById('panel');
   return {overflow:document.documentElement.scrollWidth-innerWidth,
    svgFrames:getComputedStyle(panel).borderImageSource,
    panelWidth:panel.getBoundingClientRect().width,
    buttonBg:getComputedStyle(document.querySelector('.hs-btn')).backgroundImage,
    slotBg:getComputedStyle(document.querySelector('.hs-slot')).backgroundImage,
    palette:getComputedStyle(document.documentElement).getPropertyValue('--sea').trim()
   };
  });
  assert.ok(before.overflow<=1,'Horizontal overflow at '+width);
  assert.match(before.svgFrames,/GS-UI-CMP-001_panel-frame.svg/);
  assert.match(before.slotBg,/slot-empty.svg/);
  assert.equal(before.palette,'#79BFB3');
  const assetStatus=await page.evaluate(async()=>{
   const prefix='/assets/ui/hollow-steel/batch-01/svg/';
   const names=['GS-UI-CMP-001_panel-frame.svg','GS-UI-CMP-002_primary-default.svg','GS-UI-CMP-002_primary-hover.svg','GS-UI-CMP-002_primary-pressed.svg','GS-UI-CMP-003_secondary-default.svg','GS-UI-CMP-003_button-disabled.svg','GS-UI-CMP-008_nav-idle.svg','GS-UI-CMP-008_nav-selected.svg','GS-UI-CMP-009_tab-idle.svg','GS-UI-CMP-009_tab-selected.svg','GS-UI-CMP-010_slot-empty.svg','GS-UI-CMP-010_slot-selected.svg','surface-grain.svg'];
   return await Promise.all(names.map(async name=>({name,status:(await fetch(prefix+name)).status})));
  });
  assert.deepEqual(assetStatus.filter(x=>x.status!==200),[],'Missing SVG files');
  await page.click('.hs-tab:nth-child(2)');
  assert.equal(await page.$eval('.hs-tab:nth-child(2)',x=>x.getAttribute('aria-selected')),'true');
  await page.click('.hs-navbtn:nth-child(2)');
  assert.equal(await page.$eval('.hs-navbtn:nth-child(2)',x=>x.getAttribute('aria-current')),'page');
  await page.$eval('#width',el=>{el.value='270';el.dispatchEvent(new Event('input',{bubbles:true}))});
  const after=await page.$eval('#panel',el=>el.getBoundingClientRect().width);
  assert.ok(after<=275,'Panel resizing failed');
  await page.$eval('#width',el=>{el.value='385';el.dispatchEvent(new Event('input',{bubbles:true}))});
  if(width===390)await page.screenshot({path:'screenshots/hollow-steel-390.png',fullPage:true});
  if(width===360)await page.screenshot({path:'screenshots/hollow-steel-360.png',fullPage:true});
  if(width===480)await page.screenshot({path:'screenshots/hollow-steel-480.png',fullPage:true});
  assert.deepEqual(failures,[],'Errors at '+width);
  console.log('PASS',width,JSON.stringify({overflow:before.overflow,panelWidth:before.panelWidth,resized:after,svgFiles:assetStatus.length,tabSwitch:true,navSwitch:true}));
  await page.close();
 }
}finally{await browser.close()}
