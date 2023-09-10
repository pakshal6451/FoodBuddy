import {useState,useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Button, CardActionArea, CardActions } from '@mui/material';
import axios from "axios";
import Box from '@mui/material/Box';


function RecipeManagement() {
  const [data, setData] = useState([]);

  const userGroupNumber = useSelector(
    (state) => state.groupManagementReducer.groupCode
  );
  useEffect(() => {
    axios
      .get("http://172.17.0.203:8080/api/v1/recipe/view?groupCode="+userGroupNumber)
      .then((response) => {
        console.log("recipe: ",response.data.recipeList);
        setData( response.data.recipeList);
      });
  }, []);

  return(
    <>
    <Box sx={{display:'flex',flexWrap:"wrap"}}>
    
    {data.map((r)=>(
      <Card sx={{ maxWidth: 345,m:2 }}>
          <CardActionArea>
            <CardMedia
              component="img"
              height="140"
              image={r.image}
              alt=""
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {r.title}
              </Typography>
            </CardContent>
            <CardContent>
            <a href={r.link} target='_blank' rel='noopener noreferrer'>
  <Typography variant='h5'>
    Visit the website
  </Typography>
</a>
            </CardContent>
          </CardActionArea>
        </Card> ))}
      </Box>
    </>
    
  )
}

export default RecipeManagement;
