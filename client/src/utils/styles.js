export const goldenContainerStyle = {
    background: '#B7B78A',
    margin: '0.2vw',
    padding: '0.5vw',
}

export const grayContainerStyle = {
    background: '#DDDDDD',
    margin: '0.2vw',
    padding: '0.2vw',
}

export const darkGrayContainerStyle = {
    background: '#CCCCCC',
}

export const pageStyle = {
    padding: '1vw',
}
export const createProjectButtonStyle = {
    margin: '0.2vw',
    padding: '0.8vw',
    background: '#658864',
    border: 'none',
    fontFamily: 'sans-serif'
}

export const formStyle = {
    margin: '0.2vw',
    padding: '0.5vw',
    backgroundColor: '#B7B78A',
    display: 'flex',
    flexDirection: 'column',
    gap: '12px',
    maxWidth: '30%',
}

export const labelStyle = { 
    fontWeight: 'bold',
    marginBottom: '4px', 
    display: 'block' 
}

export const inputStyle = {
    width: '100%',
    padding: '6px 8px',
    border: 'none',
    backgroundColor: '#DDDDDD',
    fontSize: '14px',
    boxSizing: 'border-box',
}

export const qualListStyle = {
    padding: '6px',
    backgroundColor: '#CCCCCC',
    maxHeight: '140px',
    overflowY: 'auto',
}

export const qualItemStyle = (selected) => ({
    padding: '4px 8px',
    cursor: 'pointer',
    backgroundColor: selected ? 'LightGreen' : 'transparent',
    userSelect: 'none',
})

export const buttonRowStyle = { display: 'flex', gap: '8px' }

export const buttonStyle = {
    padding: '7px 18px',
    backgroundColor: '#DDDDDD',
    border: 'none',
    cursor: 'pointer',
}