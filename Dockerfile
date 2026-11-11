FROM nginx:alpine
COPY index.html /usr/share/nginx/html/index.html
COPY sim.js /usr/share/nginx/html/sim.js
EXPOSE 80
