#!/bin/bash
#
# a setup script to download and install kafka, zookeeper, and storm in your
# home directory for development.
ZOOKEEPER_RELEASE='3.4.6'
KAFKA_RELEASE=0.8.1.1
STORM_RELEASE='0.9.2'

downloadToHome () {
    ROOT_URL="http://apache.mirrors.tds.net/"
    case $1 in
    zookeeper)
        URL="$1/$1-$ZOOKEEPER_RELEASE/$1-$ZOOKEEPER_RELEASE.tar.gz"
        RELEASE=$ZOOKEEPER_RELEASE
        EXT=.tar.gz
        ;;
    kafka)
        URL="$1/$KAFKA_RELEASE/$1-$KAFKA_RELEASE-src.tgz"
        RELEASE=$KAFKA_RELEASE
        EXT=.tgz
        ;;
    storm)
        URL="incubator/$1/apache-$1-$STORM_RELEASE-incubating/apache-$1-$STORM_RELEASE-incubating-src.tar.gz"
        RELEASE="$STORM_RELEASE"
        EXT=".tar.gz"
        ;;
    *)
        echo "not a valid library. choose {zookeeper/kafka/storm}"
        #exit 1
    esac
    if [[ ! -z "$RELEASE" ]]; then
        FOLDER=~/.$1-$RELEASE-src
        if [[ ! -d "$FOLDER" ]]; then
            echo "made it"
            #curl -o "$1.$EXT" $ROOT_URL$URL
            #tar -xzf "$1.$EXT" -C $FOLDER
        else
            echo "$1 may already be downloaded. Remove $FOLDER before continuing."
            #exit 1
        fi
    else
        echo '$RELEASE does not exist. this should never happen. Check script.'
        #exit 1
    fi
}
downloadToHome kafka
