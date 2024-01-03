import Tab from '@mui/material/Tab';
import { SyntheticEvent, useState } from 'react';
import { Grid, Tabs } from '@mui/material';


const ItemTabLAbel = [
  {
    menu: [
      { label: "Bordeaux", hover: { backgroundColor: '#e1bee7', color: 'inherit' } },
      { label: "Burgundy", hover: { backgroundColor: '#ff4081', color: 'white' } },
      { label: "Loire Vally", hover: { backgroundColor: 'lightgreen', color: 'green' } },
      { label: "South France", hover: { backgroundColor: 'orange', color: 'inherit' } },
      { label: "Italy", hover: { backgroundColor: 'pink', color: 'white' } },
      { label: "Spain", hover: { backgroundColor: '#ec407a', color: 'white' } },
      { label: "Germany", hover: { backgroundColor: '#90caf9', color: 'white' } },
      { label: "America", hover: { backgroundColor: '#f44336', color: 'white' } },
      { label: "Argentina", hover: { backgroundColor: '#7e57c2', color: 'white' } },
      { label: "Australia", hover: { backgroundColor: '#80cbc4', color: 'white' } },
    ],
  },
]
export default function ItemTab() {
  const [value, setlabel] = useState(0);

  const handleChange = (event: SyntheticEvent, newValue: number) => {
    setlabel(newValue);
  };

  return (
    <>
      <Grid container justifyContent="center">
        <Grid
          style={{ display: 'block', margin: 'auto' }}
          sx={{
            textAlign: { xs: 'center', md: 'center' }
          }}>
          <Tabs
            value={value}
            onChange={handleChange}
            variant="scrollable"
            scrollButtons={false}
            aria-label="scrollable prevent tabs example"
          >
            {ItemTabLAbel[0].menu.map((item, index) => (
              <Tab
                key={index}
                label={item.label}
                sx={{
                  fontSize: '12px',
                  '&:hover': {
                    backgroundColor: item.hover.backgroundColor,
                    color: item.hover.color
                  },
                }} />

            ))}


            {/* <Tab label="家品傢俬"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'white',
                  backgroundColor: '#d4e157',
                },
              }}
            />
            <Tab label="吃喝玩樂"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'white',
                  backgroundColor: '#66bb6a',
                },
              }}
            />
            <Tab label="運動旅行"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'grey',
                  backgroundColor: '#eeff41',
                },
              }}
            />
            <Tab label="玩具圖書"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'grey',
                  backgroundColor: '#fff176',
                },
              }}
            />
            <Tab label="時尚服飾"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'white',
                  backgroundColor: '#9e9e9e',
                },
              }}
            />
            <Tab label="保險金融"
              sx={{
                fontSize: '12px',
                '&:hover': {
                  color: 'white',
                  backgroundColor: '#ff7043',
                },
              }}
            /> */}
          </Tabs>
        </Grid>
      </Grid>
    </>

  );
}