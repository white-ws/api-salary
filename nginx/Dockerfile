FROM nginx:1.21.1

COPY app.conf /etc/nginx/conf.d/app.conf

WORKDIR /ops

COPY ssl.conf ssl.conf
COPY bootstrap.sh bootstrap.sh

RUN apt-get update && \
    apt-get install -y cron certbot python-certbot-nginx bash wget

CMD [ "sh", "-c", "/ops/bootstrap.sh" ]

