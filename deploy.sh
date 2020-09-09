sudo cd /home/jenkins_groovy
sudo ls
if sudo kubectl get all|grep webdeploy
then
sudo kubectl delete all --all
sudo kubectl delete pvc --all
sudo kubectl create -f /home/jenkins_groovy/webdeploy.yml
sleep 10
sudo kubectl get all
else
sudo kubectl create -f /home/jenkins_groovy/webdeploy.yml
sleep 10
sudo kubectl get all
fi
sudo kubectl cp /home/jenkins_groovy/index.html $(sudo kubectl get pod|grep webdeploy| awk '{print $1}'):/var/www/html
