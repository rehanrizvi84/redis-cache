#!/bin/sh

# Set the metadata server to the get projct id
PROJECTID=planned-commit-date
BUCKET=redis-bucket-001

echo "Project ID: ${PROJECTID} Bucket: ${BUCKET}"

# Get the files we need
gsutil cp gs://${BUCKET}/smm-reference-cache-1.0.jar .

# Install dependencies
apt-get update
apt-get -y --force-yes install openjdk-8-jdk

# Make Java 8 default
update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java

# Start server
java -jar smm-reference-cache-1.0.jar