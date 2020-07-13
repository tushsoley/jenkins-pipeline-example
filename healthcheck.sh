#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

#echo "Checking if hub is ready - $HUB_HOST"

#while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
	#do
	#	sleep 1
#done

# start the java command
echo $BROWSER
echo $HUB_HOST
echo $MODULE
echo $TAG

java -cp ci-cd.jar:ci-cd-tests.jar:libs/* -Dcucumber.options="--tags @${TAG}" -DBROWSER_NAME=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE