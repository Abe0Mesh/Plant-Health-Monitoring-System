# Plant Health Monitor 

A full-stack plant monitoring system that collects environmental sensor data, stores readings in a cloud-hosted PostgreSQL database, and displays live plant data through a dashboard.

Now my plants will never die!

## Tech Stack

| Layer       | Technologies               |
| ----------- | -------------------------- |
| Hardware    | Arduino, C++               |
| Backend     | Java, Spring Boot          |
| API         | REST, JSON                 |
| Database    | PostgreSQL                 |
| Cloud       | AWS RDS                    |
| Data Access | Spring Data JPA, Hibernate |
| Frontend    | React, JavaScript, CSS     |

## Project Structure

```text
plant-health-monitor/
├── frontend/     # React dashboard
├── backend/      # Spring Boot API and data processing
└── hardware/     # Arduino sensor code
```

## How It Works

```text
Arduino → Spring Boot → PostgreSQL (AWS RDS) → REST API → React
```

Sensor readings are collected by the Arduino and processed by the Spring Boot backend before being stored in PostgreSQL. The React frontend polls the REST API for the latest readings and updates the dashboard.

## Running Locally

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

The Spring Boot API runs on:

```text
http://localhost:8080
```

### Frontend

In a separate terminal:

```bash
cd frontend
npm install
npm run dev
```

Vite will display the local URL for the React application, typically:

```text
http://localhost:5173
```

### Hardware

Upload the Arduino program from the `hardware` directory to the connected microcontroller.

Start the backend after connecting the device so the application can read incoming serial data.


