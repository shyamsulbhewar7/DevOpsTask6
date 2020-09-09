status=$(curl -s -o /dev/null -w "%{http_code}" http://192.168.99.100:30010)
if status==200
then
exit 0
else
exit 1
fi
