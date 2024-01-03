import { styled, Stack, Autocomplete, TextField } from '@mui/material/';
// import SearchIcon from '@mui/icons-material/Search';
// import { InputBase } from '@mui/material';

// const Search = styled('div')(({ theme }) => ({
//   position: 'relative',
//   borderRadius: theme.shape.borderRadius,
//   backgroundColor: alpha(theme.palette.common.white, 0.15),
//   '&:hover': {
//     backgroundColor: alpha(theme.palette.common.white, 0.25),
//   },
//   marginRight: theme.spacing(2),
//   marginLeft: 0,
//   width: '100%',
//   [theme.breakpoints.up('sm')]: {
//     marginLeft: theme.spacing(3),
//     width: 'auto',
//   },
// }));

// const SearchIconWrapper = styled('div')(({ theme }) => ({
//   padding: theme.spacing(0, 2),
//   height: '100%',
//   position: 'absolute',
//   pointerEvents: 'none',
//   display: 'flex',
//   alignItems: 'center',
//   justifyContent: 'center',
// }));

// const StyledInputBase = styled(InputBase)(({ theme }) => ({
//   color: 'inherit',
//   '& .MuiInputBase-input': {
//     padding: theme.spacing(1, 1, 1, 0),
//     // vertical padding + font size from searchIcon
//     paddingLeft: `calc(1em + ${theme.spacing(4)})`,
//     transition: theme.transitions.create('width'),
//     width: '100%',
//     [theme.breakpoints.up('md')]: {
//       width: '90%',
//     },
//   },
// }));


const CustomTextField = styled(TextField)({
  '& label.Mui-focused': {
    color: 'white', // 入左文字後，縮上去D字既color
  },
  '& .MuiInput-underline:after': {
    borderBottomColor: 'white', // Replace 'yourUnderlineColor' with the desired color
  },
  '& .MuiOutlinedInput-root': {
    '& fieldset': {
      borderColor: 'white', //外框
    },
    '&:hover fieldset': {
      borderColor: 'white', // 外框閃一下color
    },
    '&.Mui-focused fieldset': {
      borderColor: 'white', // 外框入文字color
    },
  },
});

export default function SearchBox() {
  return (
    <>
      <Stack spacing={2} sx={{ width: "auto" }}>
        <Autocomplete
          id="free-solo-demo"
          freeSolo
          options={[]}
          renderInput={(params) => (
            <CustomTextField
              {...params}
              label="輸入品牌或產品名稱"
              sx={{ width: 500 }} // Replace 'yourWidth' with the desired width
            />
          )}
        />
      </Stack>

      {/* <Search>
        <SearchIconWrapper>
          <SearchIcon />
        </SearchIconWrapper>
        <StyledInputBase
          placeholder="輸入品牌或產品名稱:搜尋全場1,606,059件商品"
          inputProps={{ 'aria-label': 'search' }}
          sx={{ fontSize: 14, width: 600 }}
        />
      </Search> */}

    </>
  );
}