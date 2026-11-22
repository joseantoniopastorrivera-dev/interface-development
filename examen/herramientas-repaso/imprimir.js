// Imprime el HTML a PDF con Microsoft Edge: A4, sin cabecera y con solo el número de página abajo.
// Uso: node imprimir.js entrada.html salida.pdf
//
// Edge se arranca a mano en modo headless con un puerto de depuración y Playwright se conecta
// por CDP (como en las memorias de Odoo). Así se evita el --headless=old que pone
// playwright-core al lanzarlo él, que Edge ya no admite.
const path = require('path');
const os = require('os');
const fs = require('fs');
const { spawn } = require('child_process');
const { chromium } = require('playwright-core');

const EDGE = ['C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe',
              'C:/Program Files/Microsoft/Edge/Application/msedge.exe'].find(fs.existsSync);
const PUERTO = 9333;

const espera = ms => new Promise(r => setTimeout(r, ms));

(async () => {
  const [entrada, salida] = process.argv.slice(2);
  const perfil = fs.mkdtempSync(path.join(os.tmpdir(), 'repaso-edge-'));
  const edge = spawn(EDGE, ['--headless=new', '--disable-gpu', '--no-first-run',
    `--remote-debugging-port=${PUERTO}`, `--user-data-dir=${perfil}`, 'about:blank'],
    { stdio: 'ignore' });

  let navegador;
  for (let i = 0; i < 40 && !navegador; i++) {          // hasta 20 s para que abra el puerto
    await espera(500);
    navegador = await chromium.connectOverCDP(`http://127.0.0.1:${PUERTO}`).catch(() => null);
  }
  if (!navegador) throw new Error('Edge no abrió el puerto de depuración');

  try {
    const ctx = navegador.contexts()[0];
    const pagina = await ctx.newPage();
    await pagina.goto('file:///' + path.resolve(entrada).replace(/\\/g, '/'), { waitUntil: 'load' });
    await pagina.pdf({
      path: salida,
      format: 'A4',
      printBackground: true,
      displayHeaderFooter: true,
      headerTemplate: '<span></span>',
      footerTemplate: '<div style="width:100%;text-align:center;font-size:9px;font-family:Segoe UI,Arial;color:#555"><span class="pageNumber"></span></div>',
      margin: { top: '16mm', bottom: '16mm', left: '16mm', right: '16mm' },
      outline: true,
      tagged: true,
    });
  } finally {
    await navegador.close().catch(() => {});
    edge.kill();
  }
})().catch(e => { console.error(e); process.exit(1); });
