#!/bin/bash

THRESHOLD=80

# Kiểm tra Disk
DISK_USAGE=$(df / | grep / | awk '{ print $5 }' | sed 's/%//g' | head -n 1)

if [ "$DISK_USAGE" -gt "$THRESHOLD" ]; then
    echo " CANH BAO: Disk sap day ($DISK_USAGE%)"
else
    echo " Disk on dinh ($DISK_USAGE%)"
fi

# Kiểm tra RAM
TOTAL_MEM=$(free -m | grep Mem: | awk '{print $2}')
USED_MEM=$(free -m | grep Mem: | awk '{print $3}')
PERCENT=$(( 100 * USED_MEM / TOTAL_MEM ))

echo "RAM Usage: $PERCENT% ($USED_MEM MB / $TOTAL_MEM MB)"
