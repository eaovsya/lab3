# lab3 Renju game for two
Note: if you have such an error: "Error: JavaFX runtime components are missing, and are required to run this application"
try this(shamelessly copied from stackoverflow):

    File >> Project Structure >> Modules >> Dependency >> + (on left-side of window)

clicking the "+" sign will let you designate the directory where you have unpacked JavaFX's "lib" folder.

Scope is Compile (which is the default.) You can then edit this to call it JavaFX by double-clicking on the line.

then in:

    Run >> Edit Configurations

Add this line to VM Options:

--module-path /path/to/JavaFX/lib --add-modules=javafx.controls

(oh and don't forget to set the SDK)
