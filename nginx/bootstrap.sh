certbot certonly --standalone --agree-tos -m ${NGINX_EMAIL} -n -d ${NGINX_HOST}

echo "PATH=$PATH" > /etc/cron.d/certbot-renew
echo "@monthly certbot renew --nginx >> /var/log/cron.log 2>&1" >>/etc/cron.d/certbot-renew
crontab /etc/cron.d/certbot-renew

sed 's/${NGINX_HOST}/'"$NGINX_HOST"'/g' ops/ssl.conf > /etc/nginx/conf.d/ssl.conf

cron && nginx -g 'daemon off;'