upstream spring {
        server 127.0.0.1:9999;
}

server {
        server_name i7a505.p.ssafy.io;

        location /api {
                proxy_pass https://spring;
                proxy_set_header X-Real-IP $remote_addr;
                #proxy_set_header X-Forward-For $proxy_add_x_forwarded_for;
                proxy_set_header Host $host;
        }
	
	

        location / {
                root /home/ubuntu/vue;
                index index.html index.htm index.nginx-debian.html;
                try_files $uri /index.html;
        }

	listen [::]:443 ssl ipv6only=on; # managed by Certbot
    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/i7a505.p.ssafy.io/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/i7a505.p.ssafy.io/privkey.pem; # managed by Certbot
    include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot

}

