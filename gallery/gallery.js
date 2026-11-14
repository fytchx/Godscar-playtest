/* Godscar Asset Vault: standalone mock-free browser for original SVG source files. */
(() => {
  'use strict';
  const library=window.GODSCAR_GALLERY_DATA;
  if(!library||!Array.isArray(library.assets)){
    document.getElementById('results-count').textContent='Asset catalog unavailable';
    return;
  }
  const groups={
    classes:'Six complete Concept 2 class emblems, six standalone glyphs, and one shared medallion.',
    panels:'Batch 01: menu borders, modal frames, headers, insets and surface textures.',
    controls:'Batch 02: buttons, button states, navigation, tabs and dropdown frames.',
    combat:'Batch 03: combat frames, health bars, status panels and DPS shells.',
    ornaments:'Batch 04: corners, crests, sockets, slots, ribbons and dividers.',
    hollow:'Earlier Hollow Steel frames, button treatments, etched borders and utility SVGs.',
    baseline:'Original utility icons and corner ornaments from the approved Fight skeleton.',
    archive:'Older Arcana-inspired lavender icon exploration. Archived, not the selected palette.',
    all:'Every source file currently in this gallery. Review labels describe art status, not implementation.'
  };
  const $=id=>document.getElementById(id);
  const els={grid:$('gallery-grid'),search:$('asset-search'),size:$('thumb-size'),results:$('results-count'),desc:$('group-description'),more:$('load-more'),empty:$('empty-state'),nav:$('group-nav'),dialog:$('asset-dialog'),title:$('detail-heading'),subtitle:$('detail-subtitle'),stage:$('detail-stage'),preview:$('detail-preview'),sizeSelect:$('preview-size'),frameMode:$('frame-mode'),frameLabel:$('frame-mode-label'),assetId:$('detail-id'),collection:$('detail-collection'),status:$('detail-status'),path:$('detail-path'),open:$('detail-open'),download:$('detail-download'),copy:$('copy-path'),feedback:$('copy-feedback')};
  const state={group:'classes',query:'',visible:48,thumb:'standard',bg:'dark',asset:null};
  $('total-counter').textContent=String(library.total);
  for(const [group,n] of Object.entries(library.counts||{})){
    const b=els.nav.querySelector('[data-group="'+group+'"]');
    if(b){const num=b.querySelector('span');if(num)num.textContent=String(n)}
  }
  const all=els.nav.querySelector('[data-group="all"]');
  if(all)all.querySelector('span').textContent=String(library.total);
  const assetUrl=asset=>'/'+asset.path;
  const safeLabel=asset=>asset.title||asset.id;
  const isFrame=asset=>asset.type==='frame'&&(asset.group==='panels'||asset.group==='hollow'||asset.group==='combat');
  function createPreview(asset,forCard=true){
    if(isFrame(asset)){
      const el=document.createElement('div');
      el.className=forCard?'asset-frame':'detail-frame';
      el.style.borderImageSource='url("'+assetUrl(asset)+'")';
      return el;
    }
    const img=document.createElement('img');
    img.src=assetUrl(asset);
    img.loading=forCard?'lazy':'eager';
    img.decoding='async';
    img.alt=forCard?'':safeLabel(asset);
    return img;
  }
  function filtered(){
    const q=state.query.trim().toLowerCase();
    return library.assets.filter(a=>(state.group==='all'||a.group===state.group)&&(!q||[a.title,a.id,a.description,a.collection,a.path,a.group].some(t=>String(t||'').toLowerCase().includes(q))));
  }
  function buildCard(asset){
    const button=document.createElement('button');
    button.type='button';button.className='asset-card';button.dataset.group=asset.group;button.dataset.type=asset.type;
    button.setAttribute('aria-label','Inspect SVG: '+safeLabel(asset));
    const stage=document.createElement('div');stage.className='asset-canvas';
    const visual=createPreview(asset,true);visual.setAttribute('aria-hidden','true');stage.append(visual);
    const text=document.createElement('div');text.className='asset-card__caption';
    const title=document.createElement('span');title.className='asset-card__title';title.textContent=safeLabel(asset);title.title=safeLabel(asset);
    const kind=document.createElement('span');kind.className='asset-card__kind';kind.textContent=asset.collection+' · '+asset.status;
    const id=document.createElement('span');id.className='asset-card__id';id.textContent=asset.id;
    text.append(title,kind,id);button.append(stage,text);
    button.addEventListener('click',()=>openAsset(asset));return button;
  }
  function render(){
    const list=filtered(),view=list.slice(0,state.visible);
    els.grid.replaceChildren(...view.map(buildCard));
    els.grid.className='gallery-grid size-'+state.thumb;
    els.results.textContent=list.length+' of '+library.total+' assets';
    els.desc.textContent=groups[state.group]||'Browse SVG sources.';
    els.more.hidden=list.length<=state.visible;
    els.more.textContent='Show more ('+(list.length-state.visible)+' remaining) ↓';
    els.empty.hidden=list.length!==0;
    for(const b of els.nav.querySelectorAll('button[data-group]'))b.setAttribute('aria-pressed',String(b.dataset.group===state.group));
  }
  els.nav.addEventListener('click',e=>{
    const b=e.target.closest('button[data-group]');if(!b)return;
    state.group=b.dataset.group;state.visible=48;render();
  });
  els.search.addEventListener('input',()=>{
    state.query=els.search.value;state.visible=48;
    if(state.query.trim())state.group='all';
    render();
  });
  els.size.addEventListener('change',()=>{state.thumb=els.size.value;render()});
  els.more.addEventListener('click',()=>{state.visible+=48;render()});
  document.addEventListener('keydown',e=>{
    if(e.key==='/'&&document.activeElement!==els.search&&!els.dialog.open){e.preventDefault();els.search.focus()}
  });
  function openAsset(asset){
    state.asset=asset;els.title.textContent=safeLabel(asset);
    els.subtitle.textContent=asset.description||'Independent editable SVG source';
    els.assetId.textContent=asset.id;els.collection.textContent=asset.collection;
    els.status.textContent=asset.status==='Existing'?'Existing in the current baseline':asset.status==='Archive'?'Archived exploration':'Review, not approved for integration';
    els.path.textContent=asset.path;
    els.open.href=assetUrl(asset);els.download.href=assetUrl(asset);els.download.download=asset.path.split('/').pop();
    els.feedback.textContent='';els.sizeSelect.value='96';
    els.frameMode.checked=true;els.frameLabel.hidden=!isFrame(asset);
    state.bg='dark';
    updateBackground();updatePreview();
    els.dialog.showModal();
  }
  function updateBackground(){
    els.stage.className='detail-stage'+(state.bg==='dark'?'':' '+state.bg);
    for(const b of document.querySelectorAll('[data-bg]'))b.setAttribute('aria-pressed',String(b.dataset.bg===state.bg));
  }
  function updatePreview(){
    const asset=state.asset;if(!asset)return;
    const sz=Number(els.sizeSelect.value)||96;
    const asFrame=isFrame(asset)&&els.frameMode.checked;
    els.preview.replaceChildren();
    if(asFrame){
      const d=createPreview(asset,false);
      d.style.width=Math.min(sz*1.8,380)+'px';d.style.height=sz+'px';
      const w=Math.min(17,Math.max(5,Math.round(sz*.14)));
      d.style.borderWidth=w+'px';d.style.borderImageWidth=w+'px';
      els.preview.append(d);
    }else{
      const img=document.createElement('img');
      img.src=assetUrl(asset);img.alt=safeLabel(asset);
      img.style.width=sz+'px';img.style.height=sz+'px';
      img.decoding='async';els.preview.append(img);
    }
  }
  els.sizeSelect.addEventListener('change',updatePreview);
  els.frameMode.addEventListener('change',updatePreview);
  for(const b of document.querySelectorAll('[data-bg]'))b.addEventListener('click',()=>{state.bg=b.dataset.bg;updateBackground()});
  $('close-detail').addEventListener('click',()=>els.dialog.close());
  els.dialog.addEventListener('click',e=>{if(e.target===els.dialog)els.dialog.close()});
  els.copy.addEventListener('click',async()=>{
    if(!state.asset)return;
    try {
      await navigator.clipboard.writeText(state.asset.path);
      els.feedback.textContent='Source path copied.';
    } catch(e) {
      els.feedback.textContent='Clipboard unavailable. Select the source path above to copy it.';
    }
  });
  render();
})();
