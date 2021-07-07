#!/bin/sh
################################################################################
# Copyright (C) Veea Systems Limited - All Rights Reserved.
# Unauthorised copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential. [2019-2020]
################################################################################

#Generate the password
#mosquitto_passwd -c /etc/mosquitto/passwd admin
#mosquitto_passwd -b /etc/mosquitto/passwd admin admin

#start Mosquitto server first
#mosquitto -d -v -c /etc/mosquitto/conf.d/default.conf

#start AWS greengrass 
#./greengrass-entrypoint.sh &

#start homeassistant
. /app/homeassistant/venv3/bin/activate
hass  --debug -v --config /app 

while true
do
  echo sleeping...
  sleep 30
done

