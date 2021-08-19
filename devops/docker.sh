#!/bin/bash

function start-db()
{
  docker-compose -f ./docker/docker-compose.yml up -d
}

function migrate() {
        CMD="clean flyway:migrate"
      mvn -f ../pom.xml $CMD

}



function down()
{
  docker-compose -f ./docker/docker-compose.yml down
}

for cmd in "$@";
do
  $cmd
  if [ $? != 0 ]; then
    break
  fi
done