#!/bin/sh

cd $( dirname ${0})

rsync -avh --delete-during target/${1}.woa/ ${2}:~/WO/${1}.woa

eval ssh ${2} "wo-inst /root/WO/${1}.woa"