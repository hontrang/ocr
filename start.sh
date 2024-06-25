#!/bin/bash

# enter the name of script to be automatically restarted
SCRIPT="reset_all_facilities.sh"

while true; do
    ./$SCRIPT
    STATUS=$?
    if [ $STATUS -eq 0 ]; then
        break
    fi
    echo "Script crashed $STATUS. To be restarted..."
    sleep 5
done
