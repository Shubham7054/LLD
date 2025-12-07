
// File: LightRemoteBeginner.java
// A minimal Command Pattern demo with a Light (Receiver) and RemoteControl (Invoker).
// Roles:
// - Command: the interface that all commands implement (execute).
// - Concrete Commands: TurnOnLightCommand, TurnOffLightCommand.
// - Receiver: Light (does the actual work).
// - Invoker: RemoteControl (holds and triggers a command).
// - Client: main(...) wires everything together.

/////////////////////////
// COMMAND (interface) //
/////////////////////////
interface Command {
    void execute(); // one simple action
}

/////////////////////
// RECEIVER (does) //
/////////////////////
class Light {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("[Receiver: Light] Light is ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("[Receiver: Light] Light is OFF.");
    }

    public boolean isOn() {
        return isOn;
    }
}

//////////////////////////////////
// CONCRETE COMMANDS (do calls) //
//////////////////////////////////

// Turns the light ON by calling the receiver method.
class TurnOnLightCommand implements Command {
    private final Light light; // reference to Receiver

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Turns the light OFF by calling the receiver method.
class TurnOffLightCommand implements Command {
    private final Light light; // reference to Receiver

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

/////////////////////////
// INVOKER (triggers)  //
/////////////////////////
class RemoteControl {
    // The invoker only knows it has "a Command".
    // It does NOT need to know about Light or how it turns on/off.
    private Command slot;

    // Client can plug any command into the "slot".
    public void setCommand(Command command) {
        this.slot = command;
    }

    // The invoker triggers the command at the right time (e.g., when a button is pressed).
    public void pressButton() {
        if (slot == null) {
            System.out.println("[Invoker: Remote] No command set.");
            return;
        }
        System.out.println("[Invoker: Remote] Button pressed.");
        slot.execute();
    }
}

/////////////////////
// CLIENT (wiring) //
/////////////////////
public class LightRemoteBeginner {
    public static void main(String[] args) {
        // 1) Create the Receiver (the object that knows how to do the work).
        Light livingRoomLight = new Light();

        // 2) Create Concrete Commands that wrap calls to the Receiver.
        Command turnOn = new TurnOnLightCommand(livingRoomLight);
        Command turnOff = new TurnOffLightCommand(livingRoomLight);

        // 3) Create the Invoker (RemoteControl) and plug in commands.
        RemoteControl remote = new RemoteControl();

        // Turn the light ON
        remote.setCommand(turnOn);   // plug "turn on" into the invoker
        remote.pressButton();        // invoker executes the command

        // Turn the light OFF
        remote.setCommand(turnOff);  // plug "turn off" into the invoker
        remote.pressButton();        // invoker executes the command

        // You can swap commands anytime without changing the invoker or the receiver.
    }
}
