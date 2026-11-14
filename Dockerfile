FROM python:3-alpine AS art
WORKDIR /art
COPY actualart1.zip actualart2.zip actualart3.zip ./
RUN python -c "import glob,zipfile; [zipfile.ZipFile(p).extractall('/assets') for p in glob.glob('*.zip')]"

FROM nginx:alpine
COPY index.html /usr/share/nginx/html/index.html
COPY sim.js /usr/share/nginx/html/sim.js
COPY --from=art /assets /usr/share/nginx/html/assets
COPY assets/ui/ /usr/share/nginx/html/assets/ui/
EXPOSE 80
