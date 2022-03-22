# Build
```bash
mvn package
```
## DESCRIPTION

THE COMMAND WILL CAPTURE THE SCREENSHOT THEN SAVE IT AS A FILE PNG. AFTER THAT, OCR (OPTICAL CHARACTER RECOGNITION) WILL READ THE PNG FILE TO GET THE NUMBERS. THE NUMBERS THEN BEING STORED IN A TEXT FILE BEFORE BEING UPLOADED TO GOOGLE DRIVE. THE PROCESS REPEATS EVERY 2 SECONDS.

## Options
The following options are available:

```bash
-r or --resolution   set location and size for png file. There are 4 values x, y, width, height. Default: x=0, y=0, with=600, height=200
-n                   set name text file being uploaded to google drive. Default: code.txt
-c                   set credential file for google drive. Default: icauto_asics.json
-s                   set name png file that the process captured: Default: Screenshot.png


Example: java -jar ocr.jar -r 1120 150 300 100 -n code.txt -c icauto_asics.json -s screenshot.png