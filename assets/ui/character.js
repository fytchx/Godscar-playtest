'use strict';
// Character UI skeleton. Sample values only. No sim imports, gameplay writes, or saving.
(function () {
  const $ = id => document.getElementById(id);
  const screen = $('characterScreen'), stash = $('stashScreen');
  const forge=$('forgeScreen'), navigation=$('navigationScreen');
  if (!screen || !stash || !forge || !navigation) return;
  const tabs=['equipment','skills'], stashTabs=['items','materials'];
  const forgeTabs=['salvage','upgrade','reforge','imprint'];
  const navigationTabs=['regions','hunting','bosses','dungeons'];
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
  $('stashItemsList').innerHTML=items.map((item,i)=>'<button type="button" class="gs-character__item" data-item-index="'+i+'" data-rarity="'+item.rarity+'" aria-label="Inspect '+item.name+'"><span class="gs-character__item-icon">'+icon(item.slot)+'</span><span class="gs-character__item-name">'+item.name+'</span></button>').join('');
  const materials=[['Scar Shards','146','◇'],['Ember Essence','8','✹'],['Dawn Essence','5','✦'],['Grave Resin','32','◆'],['Witchglass Dust','17','✧']];
  $('stashMaterialsList').innerHTML=materials.map(m=>'<div class="gs-character__material"><span class="gs-character__material-icon" aria-hidden="true">'+m[2]+'</span><span class="gs-character__material-name">'+m[0]+'</span><strong class="gs-character__material-count">'+m[1]+'</strong></div>').join('');

  const homeNav=document.querySelector('.bottom-nav .nav-button:first-child');
  const characterNav=$('characterNav'),stashNav=$('stashNav'),forgeNav=$('forgeNav'),navigationNav=$('navigationNav');
  let lastFocus=null;
  function markNav(view){
    [[homeNav,'home'],[characterNav,'character'],[stashNav,'stash'],[forgeNav,'forge'],[navigationNav,'navigation']].forEach(([button,name])=>{
      if(!button)return;
      const active=view===name;
      button.classList.toggle('active',active);
      if(active)button.setAttribute('aria-current','page');
      else button.removeAttribute('aria-current');
    });
  }
  function selectMain(tab){
    if(!tabs.includes(tab))return;
    tabs.forEach(name=>{
      const active=name===tab,btn=screen.querySelector('[data-character-tab="'+name+'"]'),panel=$('char'+name[0].toUpperCase()+name.slice(1));
      btn.classList.toggle('active',active);btn.setAttribute('aria-selected',String(active));
      btn.tabIndex=active?0:-1;panel.hidden=!active;
    });
    $('characterViewport').scrollTop=0;
  }
  function selectStash(tab){
    if(!stashTabs.includes(tab))return;
    stashTabs.forEach(name=>{
      const active=name===tab,btn=stash.querySelector('[data-stash-tab="'+name+'"]'),panel=$('stash'+name[0].toUpperCase()+name.slice(1));
      btn.classList.toggle('active',active);btn.setAttribute('aria-selected',String(active));
      btn.tabIndex=active?0:-1;panel.hidden=!active;
    });
    $('stashViewport').scrollTop=0;
  }

  function selectForge(tab){
    if(!forgeTabs.includes(tab))return;
    forgeTabs.forEach(name=>{
      const active=name===tab,label=name[0].toUpperCase()+name.slice(1);
      const button=forge.querySelector('[data-forge-tab="'+name+'"]'),panel=$('forge'+label);
      button.classList.toggle('active',active);
      button.setAttribute('aria-selected',String(active));
      button.tabIndex=active?0:-1;
      panel.hidden=!active;
    });
    $('forgeViewport').scrollTop=0;
  }
  function selectNavigation(tab){
    if(!navigationTabs.includes(tab))return;
    navigationTabs.forEach(name=>{
      const active=name===tab,label=name[0].toUpperCase()+name.slice(1);
      const button=navigation.querySelector('[data-navigation-tab="'+name+'"]'),panel=$('navigation'+label);
      button.classList.toggle('active',active);
      button.setAttribute('aria-selected',String(active));
      button.tabIndex=active?0:-1;
      panel.hidden=!active;
    });
    $('navigationViewport').scrollTop=0;
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
    // One shared item detail dialog; attach it to whichever overlay is visible.
    (screen.hidden?stash:screen).appendChild($('characterItemLayer'));
    $('characterItemLayer').hidden=false;
    $('characterItemClose').focus({preventScroll:true});
  }
  function hideCharacter(){
    if(screen.hidden)return;
    closeItem();
    $('characterDetailsLayer').hidden=true;
    $('characterDetailsTrigger').setAttribute('aria-expanded','false');
    screen.hidden=true;screen.setAttribute('aria-hidden','true');
    document.body.classList.remove('gs-character-open');
  }
  function hideStash(){
    if(stash.hidden)return;
    closeItem();stash.hidden=true;stash.setAttribute('aria-hidden','true');
  }
  function hideForge(){
    if(forge.hidden)return;
    forge.hidden=true;forge.setAttribute('aria-hidden','true');
  }
  function hideNavigation(){
    if(navigation.hidden)return;
    navigation.hidden=true;navigation.setAttribute('aria-hidden','true');
  }
  function restoreFocus(){
    if(lastFocus&&lastFocus.isConnected)lastFocus.focus({preventScroll:true});
    else if(homeNav)homeNav.focus({preventScroll:true});
  }
  window.openCharacter=function(){
    if(!screen.hidden)return;
    lastFocus=document.activeElement;hideStash();hideForge();hideNavigation();
    screen.hidden=false;screen.setAttribute('aria-hidden','false');
    document.body.classList.add('gs-character-open');
    markNav('character');selectMain('equipment');
    $('charTabEquipment').focus({preventScroll:true});
  };
  window.closeCharacter=function(){
    if(screen.hidden)return;
    hideCharacter();markNav('home');restoreFocus();
  };
  window.openStash=function(tab='items'){
    if(!stash.hidden){selectStash(tab);return;}
    lastFocus=document.activeElement;hideCharacter();hideForge();hideNavigation();
    stash.hidden=false;stash.setAttribute('aria-hidden','false');
    markNav('stash');selectStash(tab);
    (tab==='materials'?$('stashTabMaterials'):$('stashTabItems')).focus({preventScroll:true});
  };
  window.closeStash=function(){
    if(stash.hidden)return;
    hideStash();markNav('home');restoreFocus();
  };
  window.openForge=function(tab='salvage'){
    if(!forge.hidden){selectForge(tab);return;}
    lastFocus=document.activeElement;
    hideCharacter();hideStash();hideNavigation();
    forge.hidden=false;forge.setAttribute('aria-hidden','false');
    markNav('forge');selectForge(tab);
    forge.querySelector('[data-forge-tab="'+tab+'"]').focus({preventScroll:true});
  };
  window.closeForge=function(){
    if(forge.hidden)return;
    hideForge();markNav('home');restoreFocus();
  };
  window.openNavigation=function(tab='regions'){
    if(!navigation.hidden){selectNavigation(tab);return;}
    lastFocus=document.activeElement;
    hideCharacter();hideStash();hideForge();
    navigation.hidden=false;navigation.setAttribute('aria-hidden','false');
    markNav('navigation');selectNavigation(tab);
    navigation.querySelector('[data-navigation-tab="'+tab+'"]').focus({preventScroll:true});
  };
  window.closeNavigation=function(){
    if(navigation.hidden)return;
    hideNavigation();markNav('home');restoreFocus();
  };
  window.goHome=function(){
    hideCharacter();hideStash();hideForge();hideNavigation();markNav('home');
    if(homeNav)homeNav.focus({preventScroll:true});
  };
  $('characterClose').addEventListener('click',window.closeCharacter);
  $('stashClose').addEventListener('click',window.closeStash);
  $('forgeClose').addEventListener('click',window.closeForge);
  $('navigationClose').addEventListener('click',window.closeNavigation);
  forge.querySelectorAll('[data-forge-tab]').forEach(button=>
    button.addEventListener('click',()=>selectForge(button.dataset.forgeTab)));
  navigation.querySelectorAll('[data-navigation-tab]').forEach(button=>
    button.addEventListener('click',()=>selectNavigation(button.dataset.navigationTab)));
  navigation.querySelectorAll('[data-go-navigation]').forEach(button=>
    button.addEventListener('click',()=>{
      const destination=button.dataset.goNavigation;
      selectNavigation(destination);
      const active=navigation.querySelector('[data-navigation-tab="'+destination+'"]');
      if(active)active.focus({preventScroll:true});
    }));
  screen.querySelectorAll('[data-character-tab]').forEach(button=>button.addEventListener('click',()=>selectMain(button.dataset.characterTab)));
  stash.querySelectorAll('[data-stash-tab]').forEach(button=>button.addEventListener('click',()=>selectStash(button.dataset.stashTab)));
  $('characterDetailsTrigger').addEventListener('click',openDetails);
  $('characterDetailsClose').addEventListener('click',closeDetails);
  $('characterDetailsDismiss').addEventListener('click',closeDetails);
  $('characterItemClose').addEventListener('click',closeItem);
  $('characterItemDismiss').addEventListener('click',closeItem);
  $('characterEquipmentGrid').addEventListener('click',event=>{
    const button=event.target.closest('[data-slot]');
    if(button)showItem(equipped[button.dataset.slot],button.dataset.slot);
  });
  $('stashItemsList').addEventListener('click',event=>{
    const button=event.target.closest('[data-item-index]');
    if(button){const item=items[Number(button.dataset.itemIndex)];if(item)showItem(item,item.slot);}
  });
  document.addEventListener('keydown',event=>{
    if(event.key!=='Escape'||(screen.hidden&&stash.hidden&&forge.hidden&&navigation.hidden))return;
    if(!$('characterItemLayer').hidden)closeItem();
    else if(!screen.hidden&&!$('characterDetailsLayer').hidden)closeDetails();
    else if(!screen.hidden)window.closeCharacter();
    else if(!stash.hidden)window.closeStash();
    else if(!forge.hidden)window.closeForge();
    else window.closeNavigation();
  });
})();