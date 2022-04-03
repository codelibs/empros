#!/bin/bash

cd $(dirname $0) || exit 1
base_dir=$(pwd)
work_dir="$base_dir/workspace"

echo "Build empros server."
cd ../
empros_dir=$(pwd)
cd "$empros_dir" || exit 1
mvn clean install
cd "$empros_dir/empros-server" || exit 1
mvn clean package
empros_version=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -v '^\[INFO\]' | tail -n 1)

cd "$base_dir" || exit 1
if [ -e "$work_dir" ]; then
    rm -rf "$base_dir/workspace"
fi
mkdir -p "$work_dir/empros-server"
cd "$work_dir" || exit 1

echo "Create package."
echo "$empros_version" > "$work_dir/empros-server/Version"
cp "$empros_dir/empros-server/target/empros-server-$empros_version.jar" "$work_dir/empros-server/empros-server.jar"
cp -r "$base_dir"/distribution/* "$work_dir/empros-server/"

if [ ! -e "$base_dir"/distribution/empros-server-service.exe ]; then
    echo "Download winsw."
    wget https://github.com/winsw/winsw/releases/download/v2.11.0/WinSW-x64.exe
    mv "$work_dir"/WinSW-x64.exe "$base_dir"/distribution/empros-server-service.exe
fi
cp "$base_dir"/distribution/* "$work_dir"/empros-server/

echo "Archive package."
zip -r "$base_dir"/empros-server.zip ./empros-server/*
