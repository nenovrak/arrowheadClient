cd /etc/systemd/system/; sudo find -name arrowhead-\*.service -exec sed -i 's|^\(ExecStart=.*\) -tls$|\1|' {} \; && sudo systemctl daemon-reload && sudo systemctl restart arrowhead*.service
