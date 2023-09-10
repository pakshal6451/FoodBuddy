import React, { useState } from 'react';
import axios from 'axios';
import UserData from '../Constants/UserData';
import { useNavigate } from "react-router-dom";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import logo from "../Images/download.png"

const theme = createTheme();
export const Register = () => {
  const [firstName, setFirstName] = useState(UserData.firstNameame);
  const [lastName, setLastName] = useState(UserData.lastName);
  const [password, setPassword] = useState(UserData.password);
  const [email, setEmail] = useState(UserData.email);
  const navigate = useNavigate();
  const gotoLoginPage = () => navigate("/");

  const submitForm = (event) => {
    event.preventDefault();
    console.log(`Submitted form with firstname: ${firstName}, lastname: ${lastName}, email: ${email}, and password: ${password}`);
    const formData = {
      firstName: firstName,
      lastName: lastName,
      password: password,
      email: email
    };
    callRegisterapi(formData);
    setFirstName("");
    setLastName("");
    setPassword("");
    setEmail("");
    navigate('/')
  }

  const callRegisterapi = (formData) => {
    axios.post('http://172.17.0.203:8080/api/v1/registration', formData)
      .then(response => {
        console.log(response);
        alert('User Created successfully')
      })
      .catch(error => {
        console.log(error);
      });
  };



  return (

    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${logo})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Register
            </Typography>
            <Box component="form" noValidate  sx={{ mt: 1 }}>
            <TextField value={firstName} onChange={e => setFirstName(e.target.value)}
                margin="normal"
                required
                fullWidth
                id="name"
                label="First Name"
                name="name"
                autoComplete="name"
                autoFocus
              />
              <TextField value={lastName} onChange={e => setLastName(e.target.value)}
                margin="normal"
                required
                fullWidth
                id="name"
                label="Last Name"
                name="name"
                autoComplete="name"
                autoFocus
              />
        
              <TextField value={email} onChange={e => setEmail(e.target.value)}
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField value={password} onChange={e => setPassword(e.target.value)}
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
               
              <Button onClick={submitForm}
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Register
              </Button>
              <Grid container>
                <Grid item>
                  <Link onClick={gotoLoginPage} variant="body2">
                    {"Already have an account? Login"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  )
}  