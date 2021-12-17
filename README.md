## Fix issue tess4j on Mac
Installing tess4j with home brew
```bash
brew install tesseract
```
Modify the maven cache
```bash
1. cd /Users/user/.m2/repository/net/sourceforge/tess4j/tess4j/2.0.0
2. mkdir darwin
3. jar uf tess4j-2.0.0.jar darwin
4. /usr/local/Cellar/tesseract/4.1.1/lib/libtesseract.4.dylib darwin/libtesseract.dylib
5. jar uf tess4j-2.0.0.jar darwin/libtesseract.dylib
6. jar tf tess4j-2.0.0.jar
```

# Build
```bash
mvn package
```