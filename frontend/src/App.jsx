import './App.css'
import { useEffect, useState} from "react";

function App() {

  const [reading, setReading] = useState(null);

  useEffect(() => {
    const fetchReading = () => {
      fetch("http://localhost:8080/api/readings/latest")
        .then(response =>  response.json())
        .then(data => {
        setReading(data);
        })
        .catch(error => {
          console.error("FETCH ERROR:", error);
        });
    };

    fetchReading(); // call fetch one time as browsers deployed

    const interval = setInterval(fetchReading, 1000); // Fetch data every second (Polling)

    return () => clearInterval(interval); // Cleans up when component unmounts
  
}, []); 

  if (reading === null) { // Make sure we arn't indexing into non exisiting data 
    return <p>Loading....</p>
  }
  console.log(reading);

  return (
    <div>
      <h1>Plant Monitor</h1>
      <br></br>
      <br></br>
      <p>{reading.recorded_At}</p>
      <p>Current Temperature: {reading.Fahrenheit}°F & {reading.Celsius}°C </p>
      <p>Current Humidity: {reading.Humidity}</p>
    </div>

  )
}
export default App
