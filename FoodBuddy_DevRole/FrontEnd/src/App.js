import './App.css';
import { Register } from './Components/Register';
import { Login } from './Components/Login';
import { Routing } from './Components/Routes.js'
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import Layout from './Components/Layout';
import { GroupManagement } from './Components/GroupManagement';

function App() {
  axios.interceptors.request.use(config => {
    config.headers['Origin'] = 'http://172.17.0.203:3000';
    return config;
  });

  return (
    <div className="App">
      <BrowserRouter>
        {/* <Layout> */}
          {/* This is where we can add children */}
          {/* <Login/> */},
          <Routing />
        {/* </Layout> */}
      </BrowserRouter>

      {/* <GroupManagement/> */}
    </div>
  );
}

export default App;
