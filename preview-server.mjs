import {createServer} from 'node:http';
import {readFile,stat} from 'node:fs/promises';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
const ROOT=path.dirname(fileURLToPath(import.meta.url));
const PORT=Number(process.env.PORT||3000);
const MIME={'.html':'text/html; charset=utf-8','.js':'text/javascript; charset=utf-8','.mjs':'text/javascript; charset=utf-8','.css':'text/css; charset=utf-8','.svg':'image/svg+xml','.png':'image/png','.webp':'image/webp','.jpg':'image/jpeg','.json':'application/json'};
createServer(async(req,res)=>{
 try {
  const url=new URL(req.url||'/', 'http://localhost');
  let name=decodeURIComponent(url.pathname);
  if(name==='/' )name='/index.html';
  const file=path.resolve(ROOT,'.'+name);
  if(!file.startsWith(ROOT+path.sep)){res.writeHead(403).end('Forbidden');return}
  const info=await stat(file);
  if(!info.isFile())throw new Error('Not a file');
  const data=await readFile(file);
  res.writeHead(200,{'Content-Type':MIME[path.extname(file).toLowerCase()]||'application/octet-stream','Cache-Control':path.extname(file)==='.html'?'no-store':'public, max-age=120','X-Content-Type-Options':'nosniff'});
  res.end(req.method==='HEAD'?undefined:data);
 }catch(e){res.writeHead(404,{'Content-Type':'text/plain'}).end('Not Found')}
}).listen(PORT,'0.0.0.0',()=>console.log('Godscar UI preview listening on port '+PORT));
