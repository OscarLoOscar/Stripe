import { NestedDropdown, NestedDropdownProps } from 'mui-nested-menu';
import { menuItemsData } from "./ItemMenuData"
import { Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function ContextMenu() {
  const navigate = useNavigate();

  const navigateToProductPage = (category: string) => {
    // Perform navigation to the "/product" route
    if (category)
      navigate(`${category}`);
  };

  // function navigateToProductPage(category: string) {
  //   navigate(`/product?category=${category}`);
  // }
  // const onClickHandler: NestedDropdownProps['onClick'] = (category: string) => {
  //   navigateToProductPage(category);
  // };
  const onClickHandler: ((e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void) | undefined = (e) => {
    navigateToProductPage(e.currentTarget.dataset.category as string);
  };

  return (
    <>
      <Box
        // display="flex" 
        justifyContent="space-between" alignItems="center"
        sx={{
          width: "auto",
          display: 'block',
          margin: "auto"
        }}>
        <NestedDropdown
          menuItemsData={menuItemsData}
          MenuProps={{ elevation: 3, sx: { width: '200', flex: 'none' } }}
          ButtonProps={{ variant: 'outlined', sx: { fontSize: '18px', color: 'white' } }}
          onClick={onClickHandler as NestedDropdownProps['onClick']}
        />
      </Box>
    </>
  );

}