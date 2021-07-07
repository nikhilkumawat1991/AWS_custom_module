################################################################################
# Copyright (C) Veea Systems Limited - All Rights Reserved.
# Unauthorised copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential. [2019-2020]
################################################################################
#BEGIN vhc05
#FROM arm32v7/python:3.8-alpine3.11 as build
FROM arm32v7/ubuntu:20.04 as build
#END
#BEGIN vhx09-10
#FROM arm64v8/python:3.8-alpine3.11
#END

WORKDIR /app
COPY src/  /app/

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y --no-install-recommends python3 python3-dev python3-venv python3-pip libffi-dev libssl-dev libjpeg-dev zlib1g-dev autoconf build-essential libopenjp2-7 libtiff5 tar unzip wget && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir homeassistant && cd homeassistant && python3 -m venv /app/homeassistant/venv3 && \
    . /app/homeassistant/venv3/bin/activate && \
    python3 -m pip install --upgrade pip && pip3 install wheel homeassistant==2021.1.0
RUN python3 -m pip uninstall sqlalchemy



#AWS greengrass
# Replace the args to lock to a specific version
ENV DEBIAN_FRONTEND=noninteractive 
ARG GREENGRASS_RELEASE_VERSION=2.1.0
ARG GREENGRASS_ZIP_FILE=greengrass-${GREENGRASS_RELEASE_VERSION}.zip
ARG GREENGRASS_RELEASE_URI=https://d2s8p88vqu9w66.cloudfront.net/releases/${GREENGRASS_ZIP_FILE}
ARG GREENGRASS_ZIP_SHA256=${GREENGRASS_ZIP_FILE}.sha256
COPY "${GREENGRASS_ZIP_SHA256}" /app
ENV GGC_ROOT_PATH=/app/greengrass/v2

RUN wget -q --no-check-certificate $GREENGRASS_RELEASE_URI && sha256sum -c ${GREENGRASS_ZIP_SHA256} && \
    mkdir -p /app/greengrassv2 $GGC_ROOT_PATH && unzip $GREENGRASS_ZIP_FILE -d /app/greengrassv2 && rm $GREENGRASS_ZIP_FILE && rm $GREENGRASS_ZIP_SHA256



#BEGIN vhc05
FROM arm32v7/ubuntu:20.04
#END
ENV DEBIAN_FRONTEND=noninteractive
RUN  apt-get update && apt-get upgrade -y && \
    apt install -y libffi-dev libssl-dev libjpeg-dev autoconf build-essential libopenjp2-7 libtiff5 zlib1g-dev build-essential python3 python3-dev openjdk-11-jre-headless
RUN apt-get -y install mosquitto mosquitto-clients net-tools vim 

ENV TINI_KILL_PROCESS_GROUP=1 \
    GGC_ROOT_PATH=/app/greengrass/v2 \
    PROVISION=false \
    AWS_REGION=us-east-1 \
    THING_NAME=default_thing_name \
    THING_GROUP_NAME=default_thing_group_name \
    TES_ROLE_NAME=default_tes_role_name \
    TES_ROLE_ALIAS_NAME=default_tes_role_alias_name \
    COMPONENT_DEFAULT_USER=default_component_user \
    DEPLOY_DEV_TOOLS=false \
    INIT_CONFIG=default_init_config

# Entrypoint script to install and run Greengrass
COPY "greengrass-entrypoint.sh" /app/

RUN chmod +x /app/greengrass-entrypoint.sh && \
    mkdir -p /app/greengrassv2 $GGC_ROOT_PATH

WORKDIR /app
COPY --from=build /app/greengrassv2 /app/greengrassv2


RUN useradd -ms /bin/bash homeassistant
COPY /src/configuration.yaml /app
COPY /src/default.conf /etc/mosquitto/conf.d/
COPY --chown=homeassistant:homeassistant --from=build /app/ /app/
RUN chmod 777 /app /app/homeassistant/venv3/lib/python3.8/site-packages /app/homeassistant/venv3/bin /app/homeassistant/venv3/share /etc/mosquitto/ 
RUN mkdir -p /var/lib/veea/greengrasspv/ 

RUN mkdir -p /var/lib/veea/greengrasspv/greengrasscomponents/recipes
RUN mkdir /var/lib/veea/greengrasspv/greengrasscomponents/artifacts
RUN mkdir /var/lib/veea/greengrasspv/greengrasscomponents/artifacts/1.0.0
COPY src/PubSubPublisherJava-1.0-SNAPSHOT-jar-with-dependencies.jar /var/lib/veea/greengrasspv/greengrasscomponents/artifacts/1.0.0 
COPY src/com.example.PubSubPublisherJava.yaml /var/lib/veea/greengrasspv/greengrasscomponents/recipes/ 
COPY run_component.sh /var/lib/veea/greengrasspv/
 
EXPOSE 8123
CMD ["./main.sh"]

