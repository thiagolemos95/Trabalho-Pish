
while :
do
        isoqlog &> /dev/null
	fswebcam -d /dev/video0 -r 640x480 --jpeg 85 -F 5 img.jpg
        sleep $((0,1 * 60))
	
done
