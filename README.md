# Html text Editor
## Usage instructions
* Start Main, or open TextEditor.jar

## Implementations

* Auto completion for complete tags (every tag containing the current text will be suggested)
  * If tag is no block like the "br" or "img" tag the single tag will be suggested
  * If the tag is a block like the "body" or "h1" tag a complete tag will be suggested
  * Picture of this completion: <br>![](http://i.imgur.com/F7Ifvgf.png)
  

* Perform actions with Shortcuts, a Menu Bar and a Pop up menu, as seen in following tables

| Method        | Undo          | Redo          | Auto complete | Check document |
| ------------- | ------------- | ------------- | ------------- | -------------- |
| Shortcut      | `Ctrl + Z`    | `Ctrl + Y`    | `F1`          | `F2`           |
| Menu bar      | `Option 1`    | `Option 2`    | `Option 3`    | `Option 4`     |
| Pop up menu   | `Option 1`    | `Option 2`    | `Option 3`    | `Option 4`     |

Menu bar                   |  Pop up menu
:-------------------------:|:-------------------------:
![](http://i.imgur.com/nZWSCDr.png)  |  ![](http://i.imgur.com/Kl9coL2.png)

* Undo and redo action: undo's complete actions 
  * (e.g.: if autocompletion is the completion will be undone or redone)

* Check document action: checks the document and puts a comment where the tag is missing<br>
  ![](http://i.imgur.com/QeG5Afk.png)
  
* Auto complete action: auto completes the last missing tag at position of the cursor<br>
![](http://i.imgur.com/BEC4vUu.png)

