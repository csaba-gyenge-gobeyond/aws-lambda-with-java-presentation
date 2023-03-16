#!/bin/sh

# get $.name from JSON file received on stdin
name=$(cat - | tr { '\n' | tr , '\n' | tr } '\n' | grep "name" | awk  -F'"' '{print $4}')

# generate a random name
random_name=$(curl -vs "https://randomuser.me/api/?format=yaml" 2>&1 | grep -A 3 "name:" | grep "first\|last" | tr -d '\t\n ' | sed 's/first://g' | sed 's/last:/ /g')

# convert to uppercase
echo "{ \"message\": \"Hello $name! My name is $random_name. Nice to meet you!\" }"
