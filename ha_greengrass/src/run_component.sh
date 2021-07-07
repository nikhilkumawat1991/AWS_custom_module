#!/bin/sh
################################################################################
# Copyright (C) Veea Systems Limited - All Rights Reserved.
# Unauthorised copying of this file, via any medium is strictly prohibited.
# Proprietary and confidential. [2019-2020]
################################################################################

./bin/greengrass-cli deployment create --recipeDir  /var/lib/veea/greengrasspv/greengrasscomponents/recipes/  --artifactDir /var/lib/veea/greengrasspv/greengrasscomponents/artifacts/   --merge "com.example.PubSubPublisherJava=1.0.0"
