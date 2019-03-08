sudo modprobe w1-gpio
sudo modprobe w1-therm
xterm -hold -e ' cd ~/arrowheadClient/Scripts && ./startTemp.sh ' & disown; xterm -hold -e ' cd ~/arrowheadClient/Scripts && ./startServo.sh ' & disown; xterm -hold -e ' cd ~/arrowheadClient/Scripts && ./startConsumer.sh ' & disown
