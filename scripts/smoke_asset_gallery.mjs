import puppeteer from 'puppeteer-core';
import assert from 'node:assert/strict';
import {mkdirSync} from 'node:fs';
mkdirSync('screenshots',{recursive:true});
const browser=await puppeteer.launch({executablePath:'/usr/bin/google-chrome',headless:true,args:['--no-sandbox','--disable-dev-shm-usage','--disable-gpu']});
const url='http://127.0.0.1:8080/';
try{
 for(const width of [360,390,480,1280]){
  const page=await browser.newPage();
  await page.setViewport({width,height:860,deviceScaleFactor:1});
  const errors=[];
  page.on('pageerror',e=>errors.push(e.message));
  page.on('response',r=>{if(r.status()>=400)errors.push(r.status()+' '+r.url())});
  await page.goto(url,{waitUntil:'networkidle0'});
  const initial=await page.evaluate(()=>({
    count:document.querySelectorAll('.asset-card').length,
    total:window.GODSCAR_GALLERY_DATA?.total,
    width:document.documentElement.scrollWidth-window.innerWidth,
    group:document.querySelector('[data-group="classes"]').getAttribute('aria-pressed'),
    css:!!document.querySelector('link[href="/gallery/gallery.css"]')?.sheet
  }));
  assert.equal(initial.total,223);assert.equal(initial.count,13);assert.equal(initial.group,'true');assert.equal(initial.css,true);
  assert.ok(initial.width<=1,'Initial page overflow '+width+' '+initial.width);
  for(const asset of [
    '/assets/classes/concept-02/svg/GS-CLS-ICO-001_paladin-emblem.svg',
    '/assets/ui/gilded-chrome/v1/batch-01-panels/GS-UI-CMP-001__panel-standard.svg',
    '/assets/ui/hollow-steel/depth/GS-UI-CMP-001_combat-frame.svg',
    '/assets/ui/icons/home.svg',
    '/assets/ui/arcana-preview-icons/home.svg'
  ]){
    const result=await page.evaluate(p=>fetch(p).then(r=>({status:r.status,type:r.headers.get('content-type')})),asset);
    assert.equal(result.status,200,asset);assert.match(result.type,/svg/);
  }
  await page.click('.asset-card:first-child');
  assert.equal(await page.$eval('#asset-dialog',e=>e.open),true);
  assert.match(await page.$eval('#detail-heading',e=>e.textContent),/Paladin/i);
  await page.select('#preview-size','24');
  assert.equal(await page.$eval('#detail-preview img',e=>e.style.width),'24px');
  await page.click('[data-bg="checker"]');
  assert.equal(await page.$eval('#detail-stage',e=>e.classList.contains('checker')),true);
  if(width===390)await page.screenshot({path:'screenshots/asset-gallery-detail-390.png'});
  await page.click('#close-detail');
  assert.equal(await page.$eval('#asset-dialog',e=>e.open),false);
  await page.click('[data-group="panels"]');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),29);
  await page.click('.asset-card:first-child');
  assert.equal(await page.$eval('.detail-frame',e=>!!e),true);
  await page.click('#frame-mode');
  assert.equal(await page.$eval('#detail-preview img',e=>!!e),true);
  await page.click('#close-detail');
  await page.$eval('#asset-search',el=>{el.value='shaman';el.dispatchEvent(new Event('input',{bubbles:true}))});
  assert.equal(await page.$$eval('.asset-card',e=>e.length),2);
  await page.$eval('#asset-search',el=>{el.value='';el.dispatchEvent(new Event('input',{bubbles:true}))});
  await page.click('[data-group="all"]');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),48);
  await page.click('#load-more');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),96);
  await page.click('[data-group="controls"]');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),35);
  await page.click('[data-group="hollow"]');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),28);
  await page.click('[data-group="archive"]');
  assert.equal(await page.$$eval('.asset-card',e=>e.length),12);
  await page.click('[data-group="classes"]');
  const end=await page.evaluate(()=>({overflow:document.documentElement.scrollWidth-window.innerWidth,group:document.querySelector('[data-group="classes"]').getAttribute('aria-pressed')}));
  assert.ok(end.overflow<=1,'Overflow after interactions '+width+' '+end.overflow);
  if(width===390||width===1280)await page.screenshot({path:'screenshots/asset-gallery-classes-'+width+'.png',fullPage:true});
  assert.deepEqual(errors,[],'Browser/page request errors at '+width);
  console.log('PASS',width,JSON.stringify({classItems:initial.count,totalAssets:initial.total,overflow:end.overflow,sourceFiles:true,search:true,modal:true,previewSizes:true,frame:true,categories:true}));
  await page.close();
 }
}finally{await browser.close()}
