import puppeteer from 'puppeteer-core';
import assert from 'node:assert/strict';
import {mkdirSync} from 'node:fs';
mkdirSync('screenshots',{recursive:true});
const browser=await puppeteer.launch({
 executablePath:'/usr/bin/google-chrome',
 headless:true,
 args:['--no-sandbox','--disable-dev-shm-usage','--disable-gpu']
});
try{
 for(const width of [390,1280]){
  const page=await browser.newPage();
  await page.setViewport({width,height:840,deviceScaleFactor:1});
  const errors=[];
  page.on('pageerror',e=>errors.push(e.message));
  page.on('response',r=>{if(r.status()>=400)errors.push(String(r.status())+' '+r.url())});
  await page.goto('http://127.0.0.1:8080/review/gilded-chrome-gallery.html',{waitUntil:'networkidle0'});
  for(const batch of ['1','2','3','4']){
   await page.click('[data-batch="'+batch+'"]');
   await page.waitForFunction(b=>document.querySelectorAll('.tile').length===({1:29,2:35,3:36,4:56}[b]),{},batch);
   await page.waitForFunction(()=>[...document.querySelectorAll('.canvas img')].every(i=>i.complete&&i.naturalWidth>0),{timeout:20000});
   const state=await page.evaluate(()=>({
    count:document.querySelectorAll('.tile').length,
    overflow:document.documentElement.scrollWidth-window.innerWidth,
    sourceCount:document.querySelectorAll('a[download]').length,
    frames:document.querySelectorAll('.frame').length
   }));
   assert.ok(state.overflow<=1,'Horizontal overflow: '+width+' batch '+batch);
   assert.equal(state.count,state.sourceCount,'Missing individual download link');
   if(batch!=='4') assert.ok(state.frames>=1,'No scalable frame preview at '+width+' batch '+batch);
   await page.screenshot({path:'screenshots/chrome-batch-'+batch+'-'+width+'.png',fullPage:true});
   console.log('PASS',width,'batch',batch,JSON.stringify(state));
  }
  assert.deepEqual(errors,[],'Page errors');
  await page.close();
 }
}finally{await browser.close()}
