'use strict';
// Character UI skeleton. Sample values only. No sim imports, gameplay writes, or saving.
(function () {
  const $ = id => document.getElementById(id);
  const screen = $('characterScreen');
  if (!screen) return;
  const tabs = ['equipment','inventory','skills'];
  const inventoryTabs = ['items','materials'];
  const slotPaths = {
    Weapon:'<path d="M17 3 21 7 10 18 6 14Z M7 17l-4 4 M5 12l7 7"/>',
    Helmet:'<path d="M4 16v-4a8 8 0 0 1 16 0v4H4Z M3 16h18 M9 16v4m6-4v4"/>',
    'Off-hand':'<path d="M12 2 20 6v6c0 5-3 8-8 10C7 20 4 17 4 12V6Z M12 6v12"/>',
    Amulet:'<path d="M5 3v4c0 4 3 6 7 6s7-2 7-6V3 M12 13l4 4-4 5-4-5Z"/>',
    Chest:'<path d="m7 3 5 2 5-2 5 7-4 3v9H6v-9l-4-3Z M9 6h6"/>',
    Gloves:'<path d="M4 20V9a2 2 0 0 1 4 0V5a2 2 0 0 1 4 0v3-4a2 2 0 0 1 4 0v4a2 2 0 0 1 4 0v8l-3 6H8Z"/>',
    'Ring 1':'<ellipse cx="12" cy="14" rx="7" ry="8"/><path d="m8 6 4-4 4 4-4 4Z"/>',
    Belt:'<rect x="2" y="9" width="20" height="7" rx="2"/><rect x="9" y="8" width="7" height="9" rx="1"/>',
    'Ring 2':'<ellipse cx="12" cy="14" rx="7" ry="8"/><path d="m8 6 4-4 4 4-4 4Z"/>',
    Legs:'<path d="M5 2h14v8l-3 12h-5l1-11-2 11H5L3 10Z"/>',
    Boots:'<path d="M6 2h8v10l6 4 2 4v2H2v-6l4-2Z M2 18h20"/>'
  };
  const icon = slot => '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">' + slotPaths[slot] + '</svg>';
  const equipped = {
    Weapon:{name:'Cinderbound Mace',rarity:'rare',stats:'+42 Physical Damage · +8% Attack Speed'},
    'Off-hand':{name:'Hearthguard Shield',rarity:'epic',stats:'+90 Armor · +12 Fire Resistance'},
    Helmet:{name:'Worn Greathelm',rarity:'uncommon',stats:'+16 Armor'},
    Chest:{name:'Ashen Plate',rarity:'rare',stats:'+70 Armor · +30 Max HP'},
    'Ring 1':{name:'Smoldering Band',rarity:'legendary',stats:'+18 Fire Resistance'},
    Boots:{name:'Marcher Boots',rarity:'uncommon',stats:'+11 Armor'}
  };
  const slots=['Weapon','Helmet','Off-hand','Amulet','Chest','Gloves','Ring 1','Belt','Ring 2','Legs','Boots'];
  $('characterEquipmentGrid').innerHTML=slots.map(slot=>{
    const item=equipped[slot],rarity=item?item.rarity:'common';
    const cls='gs-character__slot'+(slot==='Weapon'?' gs-character__slot--weapon':slot==='Helmet'?' gs-character__slot--helmet':slot==='Off-hand'?' gs-character__slot--offhand':slot==='Legs'?' gs-character__slot--legs':slot==='Boots'?' gs-character__slot--boots':'');
    const score=slot==='Helmet'?'<span class="gs-character__gearscore"><span>GEARSCORE</span><strong>2,485</strong></span>':'';
    return '<button type="button" class="'+cls+'" data-slot="'+slot+'" data-equipped="'+!!item+'" data-rarity="'+rarity+'" aria-label="'+slot+(item?' equipped: '+item.name:' empty')+'">'+score+'<span class="gs-character__slot-visual">'+icon(slot)+'</span><span class="gs-character__slot-label">'+slot+'</span></button>';
  }).join('');
  const items=[
    {name:'Cinderbound Mace',slot:'Weapon',rarity:'rare',stats:'+42 Physical Damage · +8% Attack Speed'},
    {name:'Pilgrim Helm',slot:'Helmet',rarity:'common',stats:'+9 Armor'},
    {name:'Graveguard Shield',slot:'Off-hand',rarity:'epic',stats:'+105 Armor'},
    {name:'Sable Amulet',slot:'Amulet',rarity:'rare',stats:'+12 INT'},
    {name:'Iron Gloves',slot:'Gloves',rarity:'uncommon',stats:'+10 Armor'},
    {name:'Ember Ring',slot:'Ring 2',rarity:'legendary',stats:'+18 Fire Resistance'},
    {name:'Warden Belt',slot:'Belt',rarity:'rare',stats:'+40 Max HP'},
    {name:'Travel Boots',slot:'Boots',rarity:'common',stats:'+8 Armor'}
  ];
  $('characterItemsList').innerHTML=items.map((item,i)=>'<button type="button" class="gs-character__item" data-item-index="'+i+'" data-rarity="'+item.rarity+'" aria-label="Inspect '+item.name+'"><span class="gs-character__item-icon">'+icon(item.slot)+'</span><span class="gs-character__item-name">'+item.name+'</span></button>').join('');
  const materials=[['Scar Shards','146','◇'],['Ember Essence','8','✹'],['Dawn Essence','5','✦'],['Grave Resin','32','◆'],['Witchglass Dust','17','✧']];
  $('characterMaterialsList').innerHTML=materials.map(m=>'<div class="gs-character__material"><span class="gs-character__material-icon" aria-hidden="true">'+m[2]+'</span><span class="gs-character__material-name">'+m[0]+'</span><strong class="gs-character__material-count">'+m[1]+'</strong></div>').join('');
  const homeNav=document.querySelector('.bottom-nav .nav-button:first-child');
  const characterNav=$('characterNav');
  let lastFocus=null;
  function markNav(active) {
    if(homeNav) {homeNav.classList.toggle('active',!active);if(active)homeNav.removeAttribute('aria-current');else homeNav.setAttribute('aria-current','page')}
    if(characterNav) {characterNav.classList.toggle('active',active);if(active)characterNav.setAttribute('aria-current','page');else characterNav.removeAttribute('aria-current')}
  }
  function selectMain(tab) {
    if(!tabs.includes(tab))return;
    for(const name of tabs){
      const active=name===tab,button=screen.querySelector('[data-character-tab="'+name+'"]'),panel=$('char'+name[0].toUpperCase()+name.slice(1));
      button.classList.toggle('active',active);button.setAttribute('aria-selected',String(active));button.tabIndex=active?0:-1;panel.hidden=!active;
    }
    if(tab==='inventory')selectInventory('items');
    $('characterViewport').scrollTop=0;
  }
  function selectInventory(tab) {
    if(!inventoryTabs.includes(tab))return;
    for(const name of inventoryTabs) {
      const active=name===tab,button=screen.querySelector('[data-inventory-tab="'+name+'"]'),panel=$('char'+name[0].toUpperCase()+name.slice(1));
      button.classList.toggle('active',active);button.setAttribute('aria-selected',String(active));button.tabIndex=active?0:-1;panel.hidden=!active;
    }
    $('characterViewport').scrollTop=0;
  }
  function closeDetails(){
    $('characterDetailsLayer').hidden=true;
    $('characterDetailsTrigger').setAttribute('aria-expanded','false');
    $('characterDetailsTrigger').focus({preventScroll:true});
  }
  function openDetails(){
    $('characterDetailsLayer').hidden=false;
    $('characterDetailsTrigger').setAttribute('aria-expanded','true');
    $('characterDetailsClose').focus({preventScroll:true});
  }
  function closeItem(){ $('characterItemLayer').hidden=true; }
  function showItem(item,slot){
    $('characterItemTitle').textContent=item?item.name:slot;
    $('characterItemSubtitle').textContent=item?item.rarity.toUpperCase()+' · '+slot:'EMPTY EQUIPMENT SLOT';
    $('characterItemStats').textContent=item?item.stats:'Nothing equipped in this slot.';
    $('characterItemLayer').hidden=false;
    $('characterItemClose').focus({preventScroll:true});
  }
  window.openCharacter=function(){
    if(!screen.hidden)return;
    lastFocus=document.activeElement;
    screen.hidden=false;screen.setAttribute('aria-hidden','false');
    document.body.classList.add('gs-character-open');
    markNav(true);selectMain('equipment');
    $('charTabEquipment').focus({preventScroll:true});
  };
  window.closeCharacter=function(){
    if(screen.hidden)return;
    closeItem();
    if(!$('characterDetailsLayer').hidden)closeDetails();
    screen.hidden=true;screen.setAttribute('aria-hidden','true');
    document.body.classList.remove('gs-character-open');
    markNav(false);
    if(lastFocus&&lastFocus.isConnected)lastFocus.focus({preventScroll:true});
  };
  $('characterClose').addEventListener('click',window.closeCharacter);
  screen.querySelectorAll('[data-character-tab]').forEach(button=>button.addEventListener('click',()=>selectMain(button.dataset.characterTab)));
  screen.querySelectorAll('[data-inventory-tab]').forEach(button=>button.addEventListener('click',()=>selectInventory(button.dataset.inventoryTab)));
  $('characterDetailsTrigger').addEventListener('click',openDetails);
  $('characterDetailsClose').addEventListener('click',closeDetails);
  $('characterDetailsDismiss').addEventListener('click',closeDetails);
  $('characterItemClose').addEventListener('click',closeItem);
  $('characterItemDismiss').addEventListener('click',closeItem);
  $('characterEquipmentGrid').addEventListener('click',event=>{
    const button=event.target.closest('[data-slot]');if(button)showItem(equipped[button.dataset.slot],button.dataset.slot);
  });
  $('characterItemsList').addEventListener('click',event=>{
    const button=event.target.closest('[data-item-index]');if(button){const item=items[Number(button.dataset.itemIndex)];if(item)showItem(item,item.slot)}
  });
  document.addEventListener('keydown',event=>{
    if(screen.hidden||event.key!=='Escape')return;
    if(!$('characterItemLayer').hidden)closeItem();
    else if(!$('characterDetailsLayer').hidden)closeDetails();
    else window.closeCharacter();
  });
})();