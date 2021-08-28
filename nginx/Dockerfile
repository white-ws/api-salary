FROM nginx:1.21.1

ARG NGINX_EMAIL
ARG NGINX_HOST

RUN apt-get update && \
    apt-get install -y cron certbot python-certbot-nginx bash wget

RUN certbot --nginx --agree-tos -m ${NGINX_EMAIL} -n -d ${NGINX_HOST}

RUN echo "PATH=$PATH" > /etc/cron.d/certbot-renew  && \
    echo "@monthly certbot renew --nginx >> /var/log/cron.log 2>&1" >>/etc/cron.d/certbot-renew && \
    crontab /etc/cron.d/certbot-renew

CMD [ "sh", "-c", "cron && nginx -g 'daemon off;'" ]

