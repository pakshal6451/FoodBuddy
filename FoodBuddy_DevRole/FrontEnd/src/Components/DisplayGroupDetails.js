import React, { useEffect,useState} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import callViewGroupDataApi from '../Constants/Constants';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Alert from '@mui/material/Alert';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));
  
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));

  
export const DisplayGroupDetails = () => {
    const api='http://172.17.0.203:8080/api/v1/groupApi/view';
    const [groupData, setGroupData] = useState([]);
    const userGroupNumber = useSelector((state) => state.groupManagementReducer.groupCode);

    const getGroupData = () => {
        const formData = {
            params : {
                groupCode : userGroupNumber
            }
        }
        axios.get(api,formData).then(response => {
            console.log(response.data.groupUsersList)
            setGroupData(response.data.groupUsersList)

        }).catch(error => {
            console.log(error);
          });

    }

    useEffect(() => {
        console.log(userGroupNumber)
        getGroupData()
      }, []);

    return(
        <div>
            <div style={{margin:"5%"}}>
            <Alert severity="success" color="info" style={{margin:"2%"}}>
                <h1>  User Group Code : {userGroupNumber} </h1>
            </Alert>
            <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">First Name</StyledTableCell>
            <StyledTableCell align="center">Last Name</StyledTableCell>
            <StyledTableCell align="center">User Email</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {groupData.map((data) => (
            <StyledTableRow
              key={data.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell align="center">{data.firstName}</TableCell>
              <TableCell align="center">{data.lastName}</TableCell>
              <TableCell align="center">{data.username}</TableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
            </div>
        </div>
    )
}