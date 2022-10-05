import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    //Create ArrayList
    ArrayList<String> arr = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String arrString = String.join(", ",arr);
            return String.format("Items: %s", arrString);
        } /*else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        }*/ else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    arr.add(parameters[1]);
                    return String.format("%s added to list!", parameters[1]);
                }
            }
            if (url.getPath().contains("/search")){
                String[] args = url.getQuery().split("=");
                if(args[0].equals("s")){
                    ArrayList<String> contains = new ArrayList<String>();
                    for (int i=0; i<arr.size();i++){
                        if (arr.get(i).contains(args[1])){
                            contains.add(arr.get(i));
                        }
                }
                String containsString = String.join(", ",contains);
                return containsString;
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
