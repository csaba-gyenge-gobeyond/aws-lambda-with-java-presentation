#!/bin/sh

function sayHello() {
  EVENT_DATA="$1"
  # get $.name from JSON data received as function argument
  name=$(echo "$EVENT_DATA" | tr { '\n' | tr , '\n' | tr } '\n' | grep "name" | awk  -F'"' '{print $4}')

  # generate a random name
  random_name=$(curl -vs "https://randomuser.me/api/?format=yaml" 2>&1 | grep -A 3 "name:" | grep "first\|last" | tr -d '\t\n ' | sed 's/first://g' | sed 's/last:/ /g')

  # print response to stdout
  echo "{ \"message\": \"Hello $name! My name is $random_name. Nice to meet you!\" }"
}
