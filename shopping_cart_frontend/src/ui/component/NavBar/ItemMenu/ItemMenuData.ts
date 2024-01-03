
interface MenuItem {
  label: string;
  onClick?: () => void;
  disabled?: boolean;
  sx?: { color: string };
  items?: MenuItem[];
  category?: string;
}

interface MenuItemsData {
  label: string;
  items: MenuItem[];
}

export const menuItemsData: MenuItemsData = {
  label: "商品分類",
  items: [
    {
      label: "Red Wine",
      items: [
        { label: "Bordeaux", category: "/product" },
        { label: "Burgundy", onClick: () => console.log("Document clicked") },
        { label: "Loire Vally", onClick: () => console.log("Document clicked") },
        { label: "South France", onClick: () => console.log("Document clicked") },
        { label: "Italy", onClick: () => console.log("Document clicked") },
        { label: "Spain", onClick: () => console.log("Document clicked") },
        { label: "Germany", onClick: () => console.log("Document clicked") },
        { label: "America", onClick: () => console.log("Document clicked") },
        { label: "Argentina", onClick: () => console.log("Document clicked") },
        { label: "Australia", onClick: () => console.log("Document clicked") },
      ],
    },
    {
      label: "White Wine",
      items: [
        { label: "Bordeaux", onClick: () => console.log("Markdown clicked") },
        { label: "Burgundy", onClick: () => console.log("Plain HTML clicked") },
        { label: "Alsace", onClick: () => console.log("Styled HTML clicked") },
        { label: "Loire Vally", onClick: () => console.log("Styled HTML clicked") },
        { label: "Rhone", onClick: () => console.log("Styled HTML clicked") },
        { label: "Piedmont", onClick: () => console.log("Styled HTML clicked") },
      ],
    },
    {
      label: "Sweet Wine",
      items: [
        { label: "Bordeaux", onClick: () => console.log("PDF clicked") },
        { label: "Germany", onClick: () => console.log("Github Gist clicked") },
      ],
    },
    {
      label: "Sparkling Wine",
      items: [
        { label: "Champagne", onClick: () => console.log("PDF clicked") },
        { label: "Burgundy", onClick: () => console.log("Github Gist clicked") },
      ],
    },
    {
      label: "Sake",
      items: [
        { label: "Junmai Daiginjo / 純米大吟醸", onClick: () => console.log("PDF clicked") },
        { label: "Junmai Ginjo / 純米吟醸", onClick: () => console.log("Github Gist clicked") },
        { label: "Tokubetsu Junmai / 特別純米", onClick: () => console.log("Github Gist clicked") },
        { label: "Tokubetsu Honjozo / 特別本醸造", onClick: () => console.log("Github Gist clicked") },
      ],
    },
  ],
};

