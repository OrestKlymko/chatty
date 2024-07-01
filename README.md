# Chatty WebSocket API

## Description

This project implements a chat API using WebSocket. It allows users to send public and private messages in a chat room.

## Build and Run

### Server Side

1. Clone the repository:
    ```bash
    git clone https://github.com/OrestKlymko/chatty.git
    cd chatty
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the server:
    ```bash
    mvn spring-boot:run
    ```

## Using WebSocket on Client

To connect to the WebSocket server on client side, we use the `@stomp/stompjs` and `sockjs-client` libraries.


