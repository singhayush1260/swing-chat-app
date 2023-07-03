# Java Swing Chat App
This is a simple chat application developed using Java Swing. The app allows users to establish a client-server connection for seamless communication. It consists of two frames: the launch frame and the chat frame.

## Launch Frame
The launch frame serves as the initial interface for the chat app. It includes the following components:

- Username Input Field: Users can enter their desired username in this field.
- PORT Number Input Field: Users need to enter a valid PORT number to establish the connection.
- Connect as Server Button: Clicking this button starts the app as a server instance.
- Connect as Client Button: Clicking this button allows the user to connect as a client to an existing server.
## Chat Frame
- The chat frame is where the actual communication takes place. Once the connection is established, users can send and receive messages. The app utilizes the Socket and ServerSocket classes from Java to achieve client-server communication. BufferedReader and PrintWriter classes are used for reading and writing messages.

## Usage
To use this app, follow these steps:

- Start the server instance by entering a valid PORT number and clicking the "Connect as Server" button.
- Clients can join the server by entering the PORT number on which the server is running and clicking the "Connect as Client" button.
- Once connected, users can send and receive messages in the chat frame.
- Please ensure that you have Java installed on your system to run the application successfully.
## Screenshots

<img width="286" alt="1" src="https://github.com/singhayush1260/swing-chat-app/assets/94789217/e1be09c3-784c-4cc1-b9e8-761a0f61a739">

<img width="287" alt="2" src="https://github.com/singhayush1260/swing-chat-app/assets/94789217/30fcdc88-738c-4078-8cab-3f03e0274661">

## Contributing
This project is open for contributions. If you would like to contribute, feel free to fork the repository, make your changes, and submit a pull request.

## License
This project is licensed under the MIT License.

