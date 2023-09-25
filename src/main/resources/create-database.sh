#!/bin/sh

MYSQL_COMMAND='/usr/local/mysql/bin/mysql'
CURRENT_DIRECTORY=`dirname $0`

WEB_SERVER_ROOT=/var/www

DOMAIN_NAME=volkswagengroup.it

cd $CURRENT_DIRECTORY

$MYSQL_COMMAND -u root -pm194asto --default-character-set=utf8 -f -e 'CREATE DATABASE `xtro_vgi` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;'

$MYSQL_COMMAND -u root -pm194asto --default-character-set=utf8 -B xtro_vgi -f -e "source ${1}"

echo "database vgi created"

mkdir -p $WEB_SERVER_ROOT/$DOMAIN_NAME

cp ./htaccess.txt $WEB_SERVER_ROOT/$DOMAIN_NAME/.htaccess

cd $WEB_SERVER_ROOT/$DOMAIN_NAME/

ln -s "../lib" .
ln -s "../interface" .
ln -s "../apps.xplants.it" .
ln -s "../cmsadmin" .
ln -s "../WebObjects" .
ln -s "../cms.xplants.it" .
ln -s "../js.xplants.net" .
ln -s "../volkswagengroup.it" .

cat > $WEB_SERVER_ROOT/$DOMAIN_NAME/google43c81229128fc67d.html << EOF
google-site-verification: google43c81229128fc67d.html
EOF
exit 0