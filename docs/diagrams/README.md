# Diagram sources

The `.mmd` files here are the source of truth for the diagrams embedded in the project README. The `.png` files are generated from them.

To regenerate after editing a source file:

```bash
npx -y @mermaid-js/mermaid-cli@11 -i architecture.mmd -o architecture.png -c theme.json -b white -s 3
```

`theme.json` holds the shared palette and spacing so every diagram renders consistently. The `-s 3` flag renders at triple resolution, which keeps the images sharp on high-density displays once the README scales them down.

PNG rather than SVG is deliberate: Mermaid emits `<foreignObject>` elements for its text labels, and browsers do not render those when an SVG is loaded through an `<img>` tag, so the labels would disappear on GitHub.
