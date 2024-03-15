public class Controller {

    ISend sender;

    void showPS() {

    }
    String read() {
        return null;
    }
    String getResponse() {
        sender.send();
        return null;
    }
    void print() {}

    void repl() {
        showPS();
        read();
        getResponse();
        print();
    }
}
