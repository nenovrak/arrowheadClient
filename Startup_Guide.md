# Arrowhead Desktop Demonstrator

## Setting up the hardware

### Hardware used

- Raspberry Pi 3 B/B+
- 1-wire temperature sensor, we used DS18B20
- Servo motor
- Breadboard
- 4.7k Ohm resistor

### Connecting the wires

The connections we made are listed below. Refer to figure 1 to find what pins to connect to.

- Temperature sensor power to pin 1, 3.3 VDC Power
- Temperature sensor Ground to pin 6, Ground
- Temperature sensor Data to pin 7, GPIO 7
- Resistor between Temperature sensor power and Data
- Servo motor Ground to pin 14, Ground
- Servo motor Power to pin 4, 5.0 VDC Power
- Servo motor Control to pin 32, GPIO 26

![Authorization_crosscheck](Images/j8header-3b.png)
*Figure 1: GPIO pinout using the Pi4J/WiringPi GPIO numbering scheme.*

If something is unclear, there are pictures of how we connected our sensor and servo to the Raspberry Pi, [here](Images/).

## Setting up the software

### Installing Raspbian

Download "Raspbian Stretch with desktop and recommended software" from [here](https://www.raspberrypi.org/downloads/raspbian/), and follow the [installation guide](https://www.raspberrypi.org/documentation/installation/installing-images/README.md).

### Installing the the arrowhead framework

Follow [this](https://github.com/arrowhead-f/core-java/blob/master/documentation/Debian%20Packages/Debian%20Install%20-%20Cutted.pdf) installation guide.

Notes:
- Make sure you select detached mode during installation.
- You need to install maven to compile. ´sudo apt update; sudo apt install maven`.

### Downloading and compiling our example

1. Run the command `git clone https://github.com/MartinBrathen/arrowheadClient.git` from your terminal.
2. Go to the *arrowheadClient* repository in your terminal.
3. Use `mvn package` inside the arrowheadClient repository.

### Some configurations that need to be made.

1. Install xterm. `sudo apt install xterm`.
2. Enable 1-wire interface. Run `raspi-config` in your terminal, press 5 to select interfaces, select 1-wire and enable it.
3. If you have set a root password you need to run `modprobe w1-gpio && modprobe w1-therm`, otherwise this will be done in the temperature provider code.

### Running the example

1. Go to the *arrowheadClient/Scripts* repository in your terminal.
2. Run the *start_ah_insecure.sh* script to start the framework. `./start_ah_insecure.sh`.
3. Run `systemctl | grep arrowhead` to make sure the framework is running. All six core systems should say "loaded active running". If this is not the case, repeat this step until it you get the desired output.
4. Run our example with the command `./startUp.sh`.
5. Three new terminal windows should pop up, and the example will shortly be running.
6. To stop the example, it is very important that you write "stop" in the terminal window of both providers before you close their window. Otherwise they will not be unregistered from the service registy, and this will lead to problems when running the example again.
